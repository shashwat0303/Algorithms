import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationTemp {
	/**
	 * Data Structure that helps us keep the track of open and closed cells
	 * on the grid.
	 */
	private WeightedQuickUnionUF tracker;
	/**
	 * status array is used to keep the track of the cells if they are open or
	 * close.
	 */
	private int[] status;
	/**
	 * variable keeps the track of the number of open sites in the grid.
	 */
	private int numberOfOpenSites = 0;
	/**
	 * size keeps the track of the number of elements in a row of the grid.
	 */
	int size;
	public PercolationTemp(int n) {
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
	
	private int arrayCell(int row, int col) {
		int cell = (row - 1) * size + col - 1;
		return cell;
	}
	
	private void validate(int row, int col) {
		
		int cell = arrayCell(row, col);
		int gridSize = size * size;
		
		if (cell < 0 || cell >= gridSize) {
			throw new IndexOutOfBoundsException("Row or Col out of range");
		}
	}
	
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
	
	public boolean isOpen(int row, int col) {
		int cell = (row - 1) * size + col - 1;
		if (status[cell] == 1) {
			return true;
		} else {
			return false;
		}
	}
	
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
	
	public boolean percolates() {
		int top = size * size;
		int bottom = top + 1;
		if (tracker.connected(top, bottom)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static void test2() {
        final PercolationTemp p = new PercolationTemp(3);
        //System.out.println(p.size);
        System.out.println("p.isOpen(4, 2) = " + p.isOpen(1, 2));
        System.out.println();
        p.open(1, 1);
        p.open(1, 2);
        p.open(1, 3);
        p.open(2, 1);
        System.out.println("p.isOpen(1, 2) = " + p.isOpen(1, 2));


        System.out.println("p.isOpen(2,2) = " + p.isOpen(2, 2));
        p.open(2, 2);
        p.open(2, 3);
        System.out.println("p.isOpen(2,2) = " + p.isOpen(2, 2));
        System.out.println("p.isFull(2, 2) = " + p.isFull(2, 2));


        System.out.println("p.isOpen(3, 1) = " + p.isOpen(3, 2));
        p.open(3, 2);
        p.open(3, 1);
        p.open(3, 3);
        System.out.println("p.isOpen(3, 1) = " + p.isOpen(3, 2));
        p.isFull(3, 2);


        System.out.println("p.percolates() = " + p.percolates());
    }
	
	public static void main(String[] args) {
	    test2();
	}
}
