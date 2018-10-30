public class Percolation {

    private int virtualTopIndex;
    private int virtualBottomIndex;

    private int size;
    private boolean[] gridStates;

    private WeightedQuickUnionUF gridUnions;
    private WeightedQuickUnionUF gridUnionsFull;

    /**
     * creates N-by-N grid, with all sites blocked
     * 
     * @param initialSize
     *            initial size
     */
    public Percolation(final int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException();
        }

        size = initialSize;

        // We are adding a virtual top and bottom
        gridStates = new boolean[initialSize * initialSize + 2];
        gridUnions = new WeightedQuickUnionUF(initialSize * initialSize + 2);
        // To avoid the back wash, we'll use another WQUF
        gridUnionsFull =
                new WeightedQuickUnionUF(initialSize * initialSize + 1);

        // both virtual nodes are open
        virtualTopIndex = initialSize * initialSize;
        virtualBottomIndex = initialSize * initialSize + 1;
        gridStates[virtualTopIndex] = true;
        gridStates[virtualBottomIndex] = true;
    }

    /**
     * open site (row i, column j) if it is not already.
     * 
     * @param row
     * @param column
     */
    public void open(final int row, final int column) {
        checkIndexInbounds(row, column);
        if (isOpen(row, column)) {
            return;
        }

        // we open the cell
        final int cellIndex = getCellIndex(row, column);

        gridStates[cellIndex] = true;

        if (row != 1 && isOpen(row - 1, column)) {
            union(cellIndex, getCellIndex(row - 1, column));
        } else if (row == 1) { // top row
            union(cellIndex, virtualTopIndex);
        }

        if (row != size && isOpen(row + 1, column)) {
            union(cellIndex, getCellIndex(row + 1, column));
        } else if (row == size) { // bottom row
            union(cellIndex, virtualBottomIndex);
        }

        if (column != 1 && isOpen(row, column - 1)) {
            union(cellIndex, getCellIndex(row, column - 1));
        }

        if (column != size && isOpen(row, column + 1)) {
            union(cellIndex, getCellIndex(row, column + 1));
        }
    }

    /**
     * is site (row i, column j) open?
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean isOpen(final int row, final int column) {
        checkIndexInbounds(row, column);
        return gridStates[getCellIndex(row, column)];
    }

    /**
     * is site (row i, column j) full?
     * 
     * @param row
     * @param column
     * @return
     */
    public boolean isFull(final int row, final int column) {
        checkIndexInbounds(row, column);
        return gridUnionsFull.connected(virtualTopIndex,
                getCellIndex(row, column));
    }

    /**
     * does the system percolate?
     * 
     * @return
     */
    public boolean percolates() {
        return gridUnions.connected(virtualTopIndex, virtualBottomIndex);
    }

    private void union(final int indexOne, final int indexTwo) {
        if (!gridUnions.connected(indexOne, indexTwo)) {
            gridUnions.union(indexOne, indexTwo);
        }
        if (indexOne <= (size * size) && indexTwo <= (size * size)
                && !gridUnionsFull.connected(indexOne, indexTwo)) {
            gridUnionsFull.union(indexOne, indexTwo);
        }
    }

    private void checkIndexInbounds(final int row, final int column) {

        if (row < 1 || row > size || column < 1 || column > size) {
            throw new IndexOutOfBoundsException("spot " + row + "," + column
                    + "is out of bounds");
        }
    }

    private int getCellIndex(final int row, final int column) {
        return (row - 1) * size + column - 1;
    }

    public static void main(String[] args) {

        checkArguments(args);
        String defaultSize = args[0];
        String numberOfExperiments = args[1];

        final PercolationStats perStats =
                new PercolationStats(Integer.parseInt(defaultSize),
                        Integer.parseInt(numberOfExperiments));
        System.out.println("mean                    = " + perStats.mean());
        System.out.println("stddev                  = " + perStats.stddev());
        System.out.println("95% confidence interval = "
                + perStats.confidenceLo() + ", " + perStats.confidenceHi());

    }

    /**
     * Checks if there are at least two arguments and both of them are numeric
     * and greater than 0
     * 
     * @param args
     */
    private static void checkArguments(final String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }

    }
}