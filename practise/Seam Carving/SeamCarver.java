import java.awt.Color;
import java.util.*;

public class SeamCarver {
    private Picture picture;
    private boolean HORIZONTAL = false;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return this.picture;
    }

    // width of current picture
    public int width() {
        return this.picture.width();
    }

    // height of current picture
    public int height() {
        return this.picture.height();
    }

    // energyVertical of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= this.picture.width() || y >= this.picture.height())
            throw new IllegalArgumentException();
        if (x == 0 || x == picture.width() - 1 || y == 0 || y == this.picture.height() - 1) {
            return 1000.00;
        }
        Color leftPixel = this.picture.get(x - 1, y);
        Color rightPixel = this.picture.get(x + 1, y);
        double rX = leftPixel.getRed() - rightPixel.getRed();
        double gX = leftPixel.getGreen() - rightPixel.getGreen();
        double bX = leftPixel.getBlue() - rightPixel.getBlue();
        double deltaX = Math.pow(rX, 2) + Math.pow(gX, 2) + Math.pow(bX, 2);

        Color upPixel = this.picture.get(x, y - 1);
        Color downPixel = this.picture.get(x, y + 1);
        double rY = upPixel.getRed() - downPixel.getRed();
        double gY = upPixel.getGreen() - downPixel.getGreen();
        double bY = upPixel.getBlue() - downPixel.getBlue();
        double deltaY = Math.pow(rY, 2) + Math.pow(gY, 2) + Math.pow(bY, 2);

        return Math.sqrt(deltaX + deltaY);

    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        this.HORIZONTAL = true;
        return findVerticalSeam();
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int currentWidth;
        int[] seam;
        double[] energy = new double[this.width() * this.height()];
        double[] disTo = new double[this.width() * this.height()];
        int[] vertexTo = new int[this.width() * this.height()];
        Queue<Integer> neighbors = new Queue<>();

        if (!this.HORIZONTAL) {
            currentWidth = this.width();
            seam = new int[this.height()];
            for (int i = 0; i < energy.length; i++) {
                energy[i] = this.energy(i % this.width(), i / this.width());
            }

        } else {
            currentWidth = this.height();
            seam = new int[this.width()];
            for (int i = 0; i < energy.length; i++) {
                energy[i] = this.energy(i / this.height(), i % this.height());
            }
        }

        for (int i = 0; i < currentWidth; i++) {
            disTo[i] = energy[i];
            vertexTo[i] = -1;
        }
        for (int i = currentWidth; i < disTo.length; i++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 0; i < energy.length - currentWidth; i++) {
            if (i % currentWidth != 0) {
                neighbors.enqueue(i + currentWidth - 1);
            }
            if (i % currentWidth != currentWidth - 1) {
                neighbors.enqueue(i + currentWidth + 1);
            }
            neighbors.enqueue(i + currentWidth);

            while (!neighbors.isEmpty()) {
                int destination = neighbors.dequeue();
                if (disTo[destination] > disTo[i] + energy[destination]) {
                    disTo[destination] = disTo[i] + energy[destination];
                    vertexTo[destination] = i;
                }
            }
        }

        //find the minimum path length
        int min = disTo.length - currentWidth;
        for (int i = disTo.length - currentWidth + 1; i < disTo.length; i++) {
            if (disTo[min] > disTo[i]) {
                min = i;
            }
        }

        int count = seam.length;
        for (int i = min; i != -1; i = vertexTo[i]) {
            seam[--count] = i % currentWidth;
        }

        if (this.HORIZONTAL == true) this.HORIZONTAL = false;
        return seam;
    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || this.height() <= 1 || seam.length != this.width())
            throw new IllegalArgumentException();

        Picture newPic = new Picture(this.width(), this.height() - 1);
        int counter = 0;

        for (int i = 0; i < seam.length; i++) {
            int vertex = seam[i];
            if (vertex < 0 || vertex >= this.height()) throw new IllegalArgumentException();
            if (i >= 1 && Math.abs(seam[i - 1] - vertex) >= 2) throw new IllegalArgumentException();

            for (int j = 0; j != vertex; j++) {
                newPic.setRGB(counter, j, this.picture.getRGB(counter, j));
            }
            for (int k = seam[i]; k < newPic.height(); k++) {
                newPic.setRGB(counter, k, this.picture.getRGB(counter, k + 1));
            }
            counter++;
        }

        this.picture = newPic;


    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        int currentWidth;
        int currentHeight;
        if (!this.HORIZONTAL) {
            currentWidth = this.width();
            currentHeight = this.height();
        } else {
            currentWidth = this.height();
            currentHeight = this.width();
        }
        if (seam == null || currentWidth <= 1 || seam.length != currentHeight)
            throw new IllegalArgumentException();


        Picture newPic = new Picture(this.width() - 1, this.height());
        int counter = 0;

        for (int i = 0; i < seam.length; i++) {
            int vertex = seam[i];
            if (vertex < 0 || vertex >= currentWidth) throw new IllegalArgumentException();
            if (i >= 1 && Math.abs(seam[i - 1] - vertex) >= 2) throw new IllegalArgumentException();

            for (int j = 0; j != vertex; j++) {
                newPic.setRGB(j, counter, this.picture.getRGB(j, counter));
            }
            for (int k = seam[i]; k < newPic.width(); k++) {
                newPic.setRGB(k, counter, this.picture.getRGB(k + 1, counter));
            }
            counter++;
        }

        this.picture = newPic;


    }


}
