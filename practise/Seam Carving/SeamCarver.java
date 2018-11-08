import java.awt.Color;
import java.lang.Math;

public class SeamCarver {
        
    private Picture pic;

    private class Node {
        public double dist;
        public int minFather;
        public final double energy;

        public Node (int x, int y) {
            dist = Double.POSITIVE_INFINITY;
            energy = energy(x, y);
            minFather = -1;
        }
    }

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new java.lang.NullPointerException();
        
        this.pic = picture;
    }
   
    // current picture
    public Picture picture() {
        return pic;
    }   
    
    // width of current picture
    public int width() {
        return pic.width();
    }
   
    // height of current picture
    public int height() {
        return pic.height();
    }
   
    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height())
            throw new java.lang.IndexOutOfBoundsException();

        int nextX = (x + 1)%width();
        int nextY = (y + 1)%height();
        int prevX = x - 1 >= 0 ? (x - 1)%height() : width() - x - 1;
        int prevY = y - 1 >= 0 ? (y - 1)%height() : height() - y - 1;

        int red, blue, green;
        red = pic.get(prevX, y).getRed() - pic.get(nextX, y).getRed();
        blue = pic.get(prevX, y).getBlue() - pic.get(nextX, y).getBlue();
        green = pic.get(prevX, y).getGreen() - pic.get(nextX, y).getGreen();
       
        int deltaX = (red*red) + (blue*blue) + (green*green);
        
        red = pic.get(x, prevY).getRed() - pic.get(x, nextY).getRed();
        blue = pic.get(x, prevY).getBlue() - pic.get(x, nextY).getBlue();
        green = pic.get(x, prevY).getGreen() - pic.get(x, nextY).getGreen();
        
        int deltaY = (red*red) + (blue*blue) + (green*green);
        
        return Math.sqrt(deltaX + deltaY);
    }

    // sequence of indices for horizontal seam (vector of y's)
    public int[] findHorizontalSeam() {
        int[] path = new int[width()];

        Node[][] matrix = findSeam(false);
        double min = Double.POSITIVE_INFINITY;
        int y = 0;
        
        for (int i = width() - 1, j = 0; j < height(); j++) {
            if (matrix[i][j].dist < min) {
                min = matrix[i][j].dist;
                y = j;
            }
        }

        for (int i = width() - 1; i >= 0; y = matrix[i][y].minFather, i--) {
            path[i] = y;
        }
        
        return path;

    }
   
    // sequence of indices for vertical seam (vector of x's)
    public int[] findVerticalSeam() {
        int[] path = new int[height()];

        Node[][] matrix = findSeam(true);

        double min = Double.POSITIVE_INFINITY;
        int x = 0;

        for (int i = 0, j = height() - 1; i < width(); i++) {
            if (matrix[i][j].dist < min) {
                min = matrix[i][j].dist;
                x = i;
            }
        }

        for (int i = height() - 1; i >= 0; x = matrix[x][i].minFather, i--) {
            path[i] = x;
        }
        
        return path;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.NullPointerException();

        if (width() == 1)
            throw new java.lang.IllegalArgumentException();
        
        for (int i = 0; i < seam.length - 1; i++)
            if (Math.abs(seam[i] - seam[i+1]) > 1)
                throw new java.lang.IllegalArgumentException();

        removeSeam(seam, false);
    }
   
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.NullPointerException();
        
        if (height() == 1)
            throw new java.lang.IllegalArgumentException();

        for (int i = 0; i < seam.length - 1; i++)
            if (Math.abs(seam[i] - seam[i+1]) > 1)
                throw new java.lang.IllegalArgumentException();
        
        removeSeam(seam, true);
    }

    private void removeSeam(int[] seam, boolean vertical) {
        
        
        int firstLim = vertical ? height() : width();
        int secondLim = vertical ? width() : height();

        Picture tmp = vertical ? 
                    new Picture(width() - 1, height()) : 
                    new Picture(width(), height() - 1);
        
        for (int i = 0; i < firstLim; i++) {
            for (int j = 0, offset = 0; j < secondLim - 1; j++) {
                if (j == seam[i])
                    offset = 1;
                
                int curLin = vertical ? i : j; 
                int curCol = vertical ? j : i;

                int lin = vertical ? i : j+offset;
                int col = vertical ? j+offset : i;
                
                tmp.set(curCol, curLin, pic.get(col, lin));
            }
        }
        
        this.pic = tmp;
    }
    
    private Node[][] findSeam(boolean vertical) {
        Node[][] matrix = new Node[width()][height()];
        int lin, col;

        int firstLim = vertical ? height() : width();
        int secondLim = vertical ? width() : height();

        for (int i = 0; i < firstLim; i++)
            for (int j = 0; j < secondLim; j++) {
                lin = vertical ? j : i;
                col = vertical ? i : j;
                matrix[lin][col] = new Node(lin, col);
            }

        for (int i = 0; i < firstLim; i++) {
            for (int j = 0; j < secondLim && i+1 < firstLim; j++) {

                for (int k = -1; k < 2; k++) {

                    lin = vertical ? j+k : i+1;
                    col = vertical ? i+1 : j+k;
                    
                    int curLin = vertical ? j : i;
                    int curCol = vertical ? i : j;
                    
                    if (i == 0 && matrix[curLin][curCol].dist == Double.POSITIVE_INFINITY)
                        matrix[curLin][curCol].dist = matrix[curLin][curCol].energy;
                    
                    if (j+k < secondLim && j+k >= 0) {
                        
                        double dist = matrix[curLin][curCol].dist;
                        double energy = matrix[lin][col].energy;
                        double nextDist = matrix[lin][col].dist;
                 
                        if (dist + energy < nextDist) {
                            matrix[lin][col].dist = dist + energy;
                            matrix[lin][col].minFather = j;
                        }
                    }
                }
            }
        }

        return matrix;
    }

    // do unit testing of this class
    public static void main(String[] args) {}
}