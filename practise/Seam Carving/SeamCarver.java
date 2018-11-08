import java.util.*;
import java.lang.*;
import java.awt.Color;
//import edu.princeton.cs.algs4.*;

public class SeamCarver {

    private double[][] energies;
    private Color[][] colors;
    private int width;
    private int height;
    private enum ORIENTATION { HORIZONTAL, VERTICAL };
    private ORIENTATION orientation;


    public SeamCarver(Picture picture){
        if (picture == null)
            throw new java.lang.IllegalArgumentException("Null picture");

        width = picture.width();
        height = picture.height();
        colors = new Color[height][width];
        orientation = ORIENTATION.VERTICAL;


        for (int row=0; row < height; row++) {
            for (int col=0; col < width; col++) {
                colors[row][col] = picture.get(col, row);
            }
        }

        fillEnergies();
    }

    public Picture picture() {
        transposeV();

        Picture picture = new Picture(width, height);
        for (int row=0; row < height; row++) {
            for (int col=0; col < width; col++) {
                picture.set(col, row, colors[row][col]);
            }
        }

        return picture;
    }

    public int width() {
        return orientation == ORIENTATION.VERTICAL ? width : height;
    }


    public int height()  {
        return orientation == ORIENTATION.VERTICAL ? height : width;
    }


    private double gradient(int col, int row) {
        Color left = colors[row][col - 1];
        Color right = colors[row][col + 1];
        Color top = colors[row - 1][col];
        Color bottom = colors[row + 1][col];

        return Math.pow((centralDifferences(left, right) +
                         centralDifferences(top, bottom)), 0.5);
    }

    private double centralDifferences(Color c1, Color c2) {
        return Math.pow(c1.getRed() - c2.getRed(), 2) +
               Math.pow(c1.getBlue() - c2.getBlue(), 2) +
               Math.pow(c1.getGreen() - c2.getGreen(), 2);
    }

    public int[] findHorizontalSeam() {
        transposeH();

        int[] seam = findVerticalSeam();

        orientation = ORIENTATION.HORIZONTAL;
        return seam;
    }

    public int[] findVerticalSeam()  {
        Point2D edgeTo[][];
        double distTo[][];

        transposeV();

        edgeTo = new Point2D[height][width];
        distTo = new double[height][width];

        initDistToAndEdgeTo(edgeTo, distTo);
        for (int row=0; row < height; row++){
            for (int col=0; col < width; col++) {
                relaxVertex(row, col, edgeTo, distTo);
            }
        }


        return trace(edgeTo, distTo);
    }

    private int[] trace(Point2D edgeTo[][], double[][] distTo) {
        int curCol = -1;
        double minDist = Double.MAX_VALUE;
        int[] seam = new int[height];

        for (int col=0; col < width; col++) {
            if (distTo[height - 1][col] < minDist) {
                curCol = col;
                minDist = distTo[height - 1][curCol];
            }
        }

        for (int row= height - 1; row > 0; row--){
            seam[row] = curCol;
            curCol = (int) edgeTo[row][curCol].y();
        }
        seam[0] = curCol;

        return seam;
    }


    private void initDistToAndEdgeTo(Point2D[][] edgeTo, double[][] distTo) {
        for (int row=0; row < height; row++) {
            for (int col=0; col < width; col++) {
                distTo[row][col] = Double.MAX_VALUE;
                edgeTo[row][col] = null;
            }
        }
        for (int col=0; col < width; col++)
            distTo[0][col] = 0;
    }

    private ArrayList<Point2D> reachableVertices(int row, int col) {
        ArrayList<Point2D> reachable = new ArrayList<Point2D>();

        if (row == height - 1) return reachable;

        if (col > 0) reachable.add(new Point2D (row + 1, col - 1));
        if (col < width - 1) reachable.add(new Point2D(row + 1, col + 1));
        reachable.add(new Point2D(row + 1, col));

        return reachable;
    }

    private void relaxVertex(int row, int col, Point2D[][] edgeTo, double[][] distTo) {

        ArrayList<Point2D> reachable = reachableVertices(row, col);
        for (Point2D sink : reachable)
            relaxEdge((int) sink.x(), (int) sink.y(), row, col, edgeTo, distTo);
    }

    private void relaxEdge(int row, int col, int sourceRow, int sourceCol, Point2D[][]edgeTo, double[][] distTo){
        double sourceDist = distTo[sourceRow][sourceCol];
        if (energies[row][col] + sourceDist < distTo[row][col]) {
            edgeTo[row][col] = new Point2D(sourceRow, sourceCol);
            distTo[row][col] = energies[row][col] + sourceDist;
        }
    }

    public void removeHorizontalSeam(int[] seam) {
        transposeH();
        removeVerticalSeam(seam);
        orientation = ORIENTATION.HORIZONTAL;
    }

    public void removeVerticalSeam(int[] seam) {
        transposeV();
        validateSeam(seam);

        Color[][] newColors = new Color[height][width - 1];
        for (int row=0; row < height; row++) {
            for (int col=0; col < width; col++) {
                if (col < seam[row])
                    newColors[row][col] = colors[row][col];
                else if (col > seam[row])
                    newColors[row][col - 1] = colors[row][col];
            }
        }
        width--;
        colors = newColors;
        fillEnergies();
    }

    private void validateSeam(int[] seam){
        if (width <= 1 ) throw new java.lang.IllegalArgumentException("Cannot remove seam");

        if (seam == null || seam.length != height) throw new java.lang.IllegalArgumentException("Invalid seam: wrong length");

        for (int i = 0; i < height - 2; i++) { 

            if (seam[i] < 0 || seam[i] >= width) throw new java.lang.IllegalArgumentException("Invalid seam: seam out of bound");

            boolean adjacentVertices = Math.abs(seam[i+1] - seam[i]) <= 1;
            if (!adjacentVertices) {
                throw new java.lang.IllegalArgumentException("Invalid seam: unadjacent vertex");
            }

        }
    }

    public double energy(int col,  int row)  {
        if (col < 0 || col >= width() || row < 0 || row >= height()){
            throw new java.lang.IllegalArgumentException("Coordinates out of bounds");
        }

        if (col == 0 || col == width() - 1 || row == 0 || row == height() - 1)
            return 1000;

        return orientation == ORIENTATION.VERTICAL ? gradient(col, row) : gradient(row, col);
    }

    private void fillEnergies() {
        energies = new double[height][width];
        for (int row=0; row < height; row++){
            for (int col=0; col < width; col++) {
                if (orientation == ORIENTATION.VERTICAL)
                    energies[row][col] = energy(col, row);
                else
                    energies[row][col] = energy(row, col);
            }
        }
    }

    private void transposeH(){
        if (orientation == ORIENTATION.VERTICAL) transpose();
        orientation = ORIENTATION.VERTICAL;
    }

    private void transposeV() {
        if (orientation == ORIENTATION.HORIZONTAL) transpose();
        orientation = ORIENTATION.VERTICAL;
    }

    private void transpose() {
        int tmp = height;
        height = width;
        width = tmp;

        Color[][] transposed = new Color[height][width];
        for (int row=0; row < height; row++) {
            for (int col=0; col < width; col++){
                transposed[row][col] = colors[col][row];
            }
        }

        colors = transposed;
        fillEnergies();
    }

}