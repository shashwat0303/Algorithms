import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 
 * @author Shashwat Koranne
 * 
 * Code writen in this file is the solution to the assigment 1 for the course,
 * 'Algorithms I' hosted on coursera.org by Princeton University.
 * 
 */
public class Percolation {
	/**
	 * Data Structure that helps us keep the track of open and closed cells
	 * on the grid.
	 */
	WeightedQuickUnionUF tracker;
	/**
	 * connection array is used to keep track of the connected cells within the 
	 * grid.
	 */
	int[] connection;
	/**
	 * status array is used to keep the track of the cells if they are open or
	 * close.
	 */
	int[] status;
	/**
	 * variable keeps the track of the number of open sites in the grid.
	 */
	int numberOfOpenSites = 0;
	/**
	 * size keeps the track of the number of elements in a row of the grid.
	 */
	int size;
	/**
	 * Percolation constructor helps in initializing the grid array.
	 */
	public Percolation(int n) {
		this.size = n;
		int gridCells = size * size + 2;
		
		tracker = new WeightedQuickUnionUF(gridCells);
		
		status = new int[gridCells];
		
		for (int i = 0; i < gridCells; i++) {
			status[i] = 0;
		}
		
		status[gridCells - 2] = 1;
		status[gridCells - 1] = 1;
	}
	
	/**
	 * open(row, col) opens the cells if they are blocked by default.
	 * @param row is the index of the row
	 * @param col is the index of the col
	 */
	public void open(int row, int col) {
		validate(row, col);
		int cell = arrayCell(row, col);
		int gridSize = size * size;
		
		if (isOpen(row, col)) {
			return;
		}
		
		status[cell] = 1;
		numberOfOpenSites++;
		
		if (row == 1) {
			tracker.union(cell, gridSize);
		}
		
		if (row == size) {
			tracker.union(cell, gridSize + 1);
		}
		
		if ((row < size) && (isOpen(row + 1, col))) {
			tracker.union(cell, arrayCell(row + 1, col));
		}
		
		if ((row > 1) && isOpen(row - 1, col)) {
			tracker.union(cell, arrayCell(row - 1, col));
		}
		
		if ((col > 1) && isOpen(row, col - 1)) {
			tracker.union(cell, arrayCell(row, col - 1));
		}
		
		if ((col < size) && isOpen(row, col + 1)) {
			tracker.union(cell, arrayCell(row, col + 1));
		}
	}

	/**
	 * A helper method that converts row and col indices of the grid to
	 * an array cell index.
	 * @param row is the row index of the grid matrix
	 * @param col is the col index of the grid matrix
	 * @return the array index
	 */
	private int arrayCell(int row, int col) {
		int cell = (row - 1) * size + col - 1;
		return cell;
	}
	
	/**
	 * Checks if the arguments row and col are valid for the respective
	 * Percolation class grid.
	 * @param row is the row index of the grid matrix
	 * @param col is the col index of the grid matrix
	 */
	private void validate(int row, int col) {
		int cell = arrayCell(row, col);
		int gridSize = size * size;
		
		if (cell < 0 || cell >= gridSize) {
			throw new IndexOutOfBoundsException("Row or Col out of range");
		}
	}
	
	/**
	 * Checks if a given cell of the matrix grid is open.
	 * @param row is the row index of the grid matrix
	 * @param col is the col index of the grid matrix
	 * @return true if the cell is open
	 */
	public boolean isOpen(int row, int col) {
		int cell = (row - 1) * size + col - 1;
		if (status[cell] == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the given cell of the matrix grid is directly connected
	 * to any of the elements in the first row.
	 * @param row is the row index of the grid matrix
	 * @param col is the col index of the grid matrix
	 * @return true if the grid cell is connected to the first row
	 */
	public boolean isFull(int row, int col) {
		int cell = arrayCell(row, col);
		if (isOpen(row, col)) {
			if (tracker.connected(cell, size * size)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Keeps the track of the number of open sites in the grid.
	 * @return number of open sites in the grid
	 */
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
	
	/**
	 * Checks if the given grid percolates.
	 * @return true if any of the bottom row elements is connected to any
	 * of the first row elements
	 */
	public boolean percolates() {
		int top = size * size;
		int bottom = top + 1;
		if (tracker.connected(top, bottom)) {
			return true;
		} else {
			return false;
		}
	}
}