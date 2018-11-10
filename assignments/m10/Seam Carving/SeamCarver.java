import java.awt.Color;
public class SeamCarver {
    private Picture pic;
    int width;
    int height;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        pic = new Picture(picture);
        width = width();
        height = height();
    }
    // current picture
    public Picture picture() {
        return new Picture(pic);
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
            throw new IndexOutOfBoundsException();
        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1)
            return 1000;
        return Math.sqrt(gradient(pic.get(x - 1, y), pic.get(x + 1, y)) + gradient(pic.get(x, y - 1), pic.get(x, y + 1)));
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        for (int row = 0; row < height; row++) {
            distTo[row][0] = 1000;
        }
        for (int col = 0; col < width - 1; col++) {
            for (int row = 0; row < height; row++) {
                relaxH(row, col, edgeTo, distTo);
            }
        }
        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int row = 0; row < height; row++) {
            if (minDist > distTo[row][width - 1]) {
                minDist = distTo[row][width - 1];
                minRow = row;
            }
        }
        int[] indices = new int[width];
        for (int col = width - 1, row = minRow; col >= 0; col--) {
            indices[col] = row;
            row -= edgeTo[row][col];
        }
        return indices;
    }
    private void relaxH(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextCol = col + 1;
        for (int i = -1; i <= 1; i++) {
            int nextRow = row + i;
            if (nextRow < 0 || nextRow >= height) continue;
            if (i == 0) {
                if (distTo[nextRow][nextCol] >= distTo[row][col]  + energy(nextCol, nextRow)) {
                    distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
                    edgeTo[nextRow][nextCol] = i;
                }
            }
            if (distTo[nextRow][nextCol] > distTo[row][col]  + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] energy = new double[height][width];
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        int[] indices = new int[height];
        if (width == 1 || height == 1) {
            return indices;
        }
        for (int i = 0; i < width; i++) {
            distTo[0][i] = 1000.0;
        }
        // this is for relaxation.
        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < width; j++) {
                relaxV(i, j, edgeTo, distTo);
            }
        }
        // calculating from last row
        // column wise
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int col = 0; col < width; col++) {
            if (minDist > distTo[height - 1][col]) {
                minDist = distTo[height - 1][col];
                minCol = col;
            }
        }
        //indices values of shortest path.
        for (int row = height - 1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        indices[0] = indices[1];
        return indices;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        for (int col = 0; col < width; col++) {
            for (int row = seam[col]; row < height - 1; row++) {
                this.pic.set(col, row, this.pic.get(col, row + 1));
            }
        }
        height--;
    }
    private void reset(double[][] distTo) {
        /**
         *reset all the values to maxvalue.
         */
        for (int i = 0; i < distTo.length; i++) {
            for (int j = 0; j < distTo[i].length; j++) {
                distTo[i][j] = Double.MAX_VALUE;
            }
        }
    }
    private void relaxV(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextRow = row + 1;
        for (int i = -1; i <= 1; i++) {
            int nextCol = col + i;
            if (nextCol < 0 || nextCol >= width()) {
                continue;
            }
            //spl case for bottom element.
            if (i == 0) {
                if (distTo[nextRow][nextCol] >= distTo[row][col] + energy(nextCol, nextRow)) {
                    distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                    edgeTo[nextRow][nextCol] = i;
                }
            }
            if (distTo[nextRow][nextCol] > distTo[row][col] + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        for (int row = 0; row < height; row++) {
            for (int col = seam[row]; col < width - 1; col++) {
                this.pic.set(col, row, this.pic.get(col + 1, row));
            }
        }
        width--;
    }
    private double gradient(Color x, Color y) {
        double r = x.getRed() - y.getRed();
        double g = x.getGreen() - y.getGreen();
        double b = x.getBlue() - y.getBlue();
        return r * r + g * g + b * b;
    }
}