public class PercolationStats {

  private int n;
  private int gridSize;
  private int nTests;
  private int[] openSites;
  private double[] threshold;
  

  /**
   * Constructor used to perform t independent tests on a n-by-b grid
   * @param n the side of the n-by-b grid
   * @param t the number of independent tests to run
   */
  public PercolationStats(int n, int t) {
    if (n < 1 || t < 1)
      throw new java.lang.IllegalArgumentException();
    
    Percolation percolationTest;
    
    this.n = n;
    gridSize = n * n;
    nTests = t;
    openSites = new int[t];
    threshold = new double[t];
    int counter;
    int r;
    int c;

    // Build an array that will later be shuffled to determine in which
    // order the sites will be opened
    int[] siteQueue = new int[n * n];
    for (int i = 0; i < n * n; i++) {
      siteQueue[i] = i;
    }

    // Perform the tests
    for (int i = 0; i < nTests; i++) {
      percolationTest = new Percolation(n);
      StdRandom.shuffle(siteQueue);
      counter = 0;

      while (!percolationTest.percolates()) {
        r = rowFromIndex(siteQueue[counter]);
        c = columnFromIndex(siteQueue[counter]);
        percolationTest.open(r, c);
        counter++;
      }

      openSites[i] = counter;
      threshold[i] = (double) counter / gridSize;
    }

  }

  public double mean() {
    return StdStats.mean(threshold);
  }

  public double stddev() {
    return StdStats.stddev(threshold);
  }

  public double confidenceLo() {
    return mean() - (1.96 * stddev() / Math.sqrt(nTests));
  }

  public double confidenceHi() {
    return mean() + (1.96 * stddev() / Math.sqrt(nTests));
  }

  /**
   * Method used to return the row index when given a one-dimensional position
   * like an array index
   * @param p the array index
   * @return the row index
   */
  private int rowFromIndex(int p) {
    return p / n + 1;
  }

  /**
   * Method used to return the column index when given a one-dimensional
   * position like an array index
   * @param p  the array index
   * @return the column index
   */
  private int columnFromIndex(int p) {
    return p % n + 1;
  }

  public static void main(String[] args) {

    int side = Integer.parseInt(args[0]);
    int tests = Integer.parseInt(args[1]);

    PercolationStats ps = new PercolationStats(side, tests);

    System.out.println("mean\t\t\t= " + ps.mean());
    System.out.println("stddev\t\t\t= " + ps.stddev());
    System.out.println("95% confidence interval = " + ps.confidenceLo() + ", "
        + ps.confidenceHi());
  }

}
