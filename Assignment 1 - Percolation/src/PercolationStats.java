import edu.princeton.cs.algs4.StdRandom;

/**
 * 
 * @author Shashwat Koranne
 * 
 * Code writen in this file is the solution to the assigment 1 for the course,
 * 'Algorithms I' hosted on coursera.org by Princeton University.
 * 
 */
public class PercolationStats {
	/**
	 * Percolation class instant which initializes the grid of size n X n.
	 */
	Percolation myPerc;
	/**
	 * Holds the value of the grid size.
	 */
	int gridSize;
	/**
	 * Keeps the track of number of Monte - Carlo Simulations to be done.
	 */
	int numberOfTrials;
	/**
	 * Keeps the track of percolation threshold values for every trial.
	 */
	double[] percolationThreshold;
	/**
	 * Percolation stats constructor to initialize the Percolation instant and
	 * carry out Monte - Carlo Simulations.
	 * @param n size of the matrix
	 * @param trials number of trials for Monte - Carlo Simulations
	 */
	public PercolationStats(int n, int trials) {
		this.gridSize = n * n;
		this.numberOfTrials = trials;
		percolationThreshold = new double[trials];
		for (int i = 0; i < trials; i++) {
			myPerc = new Percolation(n);
			while(!myPerc.percolates()) {
				int cellIndex = StdRandom.uniform(gridSize);
				int row = (int) cellIndex / n + 1;
				int col = (int) cellIndex % n + 1;
				myPerc.open(row, col);
			}
			percolationThreshold[i] = (double) myPerc.numberOfOpenSites / this.gridSize;
		}
	}
	
	/**
	 * Calculates the mean of the percolation thresholds.
	 * @return mean of the percolation thresholds
	 */
	public double mean() {
		
		int length = percolationThreshold.length;
		double sumOfThresholds = 0.0;
		for (int i = 0; i < length; i++) {
			sumOfThresholds = sumOfThresholds + percolationThreshold[i];
		}
		double mean = (double) sumOfThresholds / numberOfTrials;
		return mean;
	}
	
	/**
	 * Calculates the Standard Deviation of the percolation thresholds.
	 * @return Standard Deviation of the percolation thresholds
	 */
	public double stddev() {
		double mean = mean();
		int length = percolationThreshold.length;
		double sumOfSquares = 0;
		for (int i = 0; i < length; i++) {
			double square = Math.pow((percolationThreshold[i] - mean), 2);
			sumOfSquares = sumOfSquares + square;
		}
		double variance = sumOfSquares / (numberOfTrials - 1);
		double stddev = Math.sqrt(variance);
		return stddev;
	}
	
	/**
	 * Calculates the lower value of the Standard Deviation of 
	 * percolation thresholds.
	 * @return Lower limit of the Standard Deviation of the percolation thresholds
	 */
	public double confidenceLo() {
		double mean = mean();
		double stddev = stddev();
		double trialSqrt = Math.sqrt(numberOfTrials);
		double val = 1.96 * stddev / trialSqrt;
		double confidenceLo = mean - val;
		return confidenceLo;
	}
	
	/**
	 * Calculates the higher value of the Standard Deviation of 
	 * percolation thresholds.
	 * @return Upper limit of the Standard Deviation of the percolation thresholds
	 */
	public double confidenceHi() {
		double mean = mean();
		double stddev = stddev();
		double trialSqrt = Math.sqrt(numberOfTrials);
		double val = 1.96 * stddev / trialSqrt;
		double confidenceHi = mean + val;
		return confidenceHi;
	}
	
	public static void main(String[] args) {
		PercolationStats stat = new PercolationStats(50, 100);
		double mean = stat.mean();
		double stddev = stat.stddev();
		double lo = stat.confidenceLo();
		double hi = stat.confidenceHi();
		System.out.println("mean = " + mean);
		System.out.println("stddev = " + stddev);
		System.out.println("confidence interval = " + lo + ", " + hi);
	}
}
