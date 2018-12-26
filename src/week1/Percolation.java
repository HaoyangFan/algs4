// TODO: remove the package statement before you submit the assignment
package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * My implementation of Coursera Algorithms I programming assignment #1: Percolation
 * (see description: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)
 * <p>
 * Ideas:
 * 1) Internally maintain a union find data structure to quickly union adjacent open sites
 * and check if some sites have been connected with other sites. This union-find
 * data structure will be using to check if system percolates.
 * <p>
 * 2) Maintain another union find data structure to help us check if certain sites
 * are full. The reason we'll need this one is to avoid the issue of backwash.
 * <p>
 * 3) Additionally create a virtual site just to help indicate that whether a particular
 * site has been connected to the top border site
 * <p>
 * 4) Additionally create a virtual site just to help indicate that whether a particular
 * site has been connected to the bottom border site
 * <p>
 * 5) In the end, just check if these two virtual sites have been connected to each
 * other. If so, we can say the system percolates.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-5-2018
 */
public class Percolation {
    /**
     * Base data structure for week1 application for fast union and find.
     */
    private final WeightedQuickUnionUF uf;
    /**
     * Another data structure to help us check if some sites are full.
     */
    private final WeightedQuickUnionUF uf2;
    /**
     * Keep track of current number of open spots in the grid.
     */
    private int numOfOpenSpots;
    /**
     * Keep track of whether each site is open or not.
     */
    private final boolean[] open;
    /**
     * Record the number of rows and columns in the grid.
     */
    private final int numOfRows;


    /**
     * Create n-by-n grid, with all sites initially blocked.
     *
     * @param n the number of rows and columns in the grid
     * @throws IllegalArgumentException for n <= 0
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Cannot create a empty grid");
        }
        // additionally create two virtual node: one for top and one for bottom
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
        // initialize the count of open sites to be zero
        numOfOpenSpots = 0;
        // mark all sites as initially blocked
        open = new boolean[n * n];
        // save the number of rows and columns
        numOfRows = n;
    }

    /**
     * Convert 2D index into 1D index.
     *
     * @param row the row index
     * @param col the column index
     * @return the 1D index corresponding to the 2D index
     */
    private int convert2Dto1D(int row, int col) {
        return (row - 1) * numOfRows + col - 1;
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && row <= numOfRows && col >= 1 && col <= numOfRows && open[convert2Dto1D(row, col)];
    }

    private void connect(int row, int col) {
        int currIdx = convert2Dto1D(row, col);
        // top, bottom, left, right
        int[] directX = {-1, 1, 0, 0};
        int[] directY = {0, 0, -1, 1};
        for (int i = 0; i < directX.length; i++) {
            int idx = convert2Dto1D(row + directX[i], col + directY[i]);
            // check if this position is valid
            if (isValid(row + directX[i], col + directY[i])) {
                uf.union(currIdx, idx);
                uf2.union(currIdx, idx);
            }
        }
    }

    /**
     * Open site (row, col) if it is not open already.
     *
     * @param row the row index of site to open
     * @param col the column index of site to open
     * @throws IllegalArgumentException for row <= 0 or row > n or col <= 0 or col > n
     */
    public void open(int row, int col) {
        if (row <= 0 || row > numOfRows || col <= 0 || col > numOfRows) {
            throw new IllegalArgumentException("Parameters to open() should in range 1 to n");
        }
        int curr1DIdx = convert2Dto1D(row, col);
        // in case the site is already opened
        if (isOpen(row, col)) {
            return;
        }
        // update the open of current site to be open
        open[curr1DIdx] = true;
        // increment the number of open sites in current grid
        numOfOpenSpots++;
        // in case the site is on the top border, connecting it with top virtual site
        if (row == 1) {
            uf.union(curr1DIdx, numOfRows * numOfRows);
            uf2.union(curr1DIdx, numOfRows * numOfRows);
        }
        // in case the site is on the bottom border, connecting it with bottom virtual site
        if (row == numOfRows) {
            uf.union(curr1DIdx, numOfRows * numOfRows + 1);
        }
        // connect adjacent open sites
        connect(row, col);
    }

    /**
     * Check if site (row, col) is open.
     *
     * @param row the row index of site to check
     * @param col the column index of site to check
     * @throws IllegalArgumentException for row <= 0 or row > n or col <= 0 or col > n
     */
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > numOfRows || col <= 0 || col > numOfRows) {
            throw new IllegalArgumentException("Parameters to open() should in range 1 to n");
        }
        return open[convert2Dto1D(row, col)];
    }

    /**
     * Check if site (row, col) is full.
     *
     * @param row the row index of site to check
     * @param col the column index of site to check
     * @throws IllegalArgumentException for row <= 0 or row > n or col <= 0 or col > n
     */
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > numOfRows || col <= 0 || col > numOfRows) {
            throw new IllegalArgumentException("Parameters to open() should in range 1 to n");
        }
        // check if current site has been connected to virtual top site
        return uf2.connected(convert2Dto1D(row, col), numOfRows * numOfRows);
    }

    /**
     * Get the number of open sites in current grid.
     *
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return numOfOpenSpots;
    }

    /**
     * Check if the current system percolates.
     *
     * @return true if current system percolates; false otherwise
     */
    public boolean percolates() {
        // check if top virtual site has been connected to bottom virtual site
        return uf.connected(numOfRows * numOfRows, numOfRows * numOfRows + 1);
    }

    // test client
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(5, 1);
        System.out.println(p.isFull(5, 1)); // false
        System.out.println(p.isOpen(5, 1)); // true
        System.out.println(p.percolates()); // false
        p.open(4, 1);
        System.out.println(p.isFull(4, 1));  // false
        p.open(2, 1);
        System.out.println(p.isFull(2, 1));  // false
        p.open(1, 1);
        System.out.println(p.isFull(2, 1));  // true
        System.out.println(p.percolates()); // false;
        p.open(3, 1);
        System.out.println(p.percolates()); // true
        System.out.println(p.isFull(4, 1)); // true
        p.open(5, 4);
        System.out.println(p.isFull(5, 4));  // false
    }
}
