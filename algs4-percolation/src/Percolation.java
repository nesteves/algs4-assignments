public class Percolation {

  private int n;
  private WeightedQuickUnionUF uf;
  private WeightedQuickUnionUF ufBackwash;
  private boolean[] grid;

  /**
   * Constructor to set up the grid and necessary properties
   * @param n the side of the n-by-b grid
   */
  public Percolation(int n) {
    if (n <= 0)
      throw new java.lang.IllegalArgumentException();

    this.n = n;
    uf = new WeightedQuickUnionUF(n * n + 2);
    ufBackwash = new WeightedQuickUnionUF(n * n + 1);
    grid = new boolean[n * n];
  }

  /**
   * Method used to open a single node, given its coordinates, and to connect it
   * to other adjacent open nodes
   * @param i the row
   * @param j the column
   */
  public void open(int i, int j) {
    if (i < 1 || i > n || j < 1 || j > n)
      throw new java.lang.IndexOutOfBoundsException();

    int position = convertCoordinates(i, j);
    grid[position] = true;

    unionAbove(position);
    unionBelow(position);
    unionLeft(position);
    unionRight(position);

  }

  /**
   * Method used to determine whether a particular node is open
   * 
   * @param i the row
   * @param j the column
   * @return true if the node is open, false otherwise
   */
  public boolean isOpen(int i, int j) {
    if (i < 1 || i > n || j < 1 || j > n)
      throw new java.lang.IndexOutOfBoundsException();

    int position = convertCoordinates(i, j);

    return grid[position];
  }

  /**
   * Method used to determine whether a particular node is full, that is,
   * connected to the topmost node.
   * 
   * @param i the row
   * @param j the column
   * @return true if the node is full, false otherwise
   */
  public boolean isFull(int i, int j) {
    if (i < 1 || i > n || j < 1 || j > n)
      throw new java.lang.IndexOutOfBoundsException();

    int position = convertCoordinates(i, j);

    return ufBackwash.connected(position, n * n);
  }

  /**
   * Method used to determine whether the system percolates - if the top node is
   * connected to the bottom node
   * @return true if both the top and bottom nodes are connected, false
   *         otherwise
   */
  public boolean percolates() {
    return uf.connected(n * n, n * n + 1);
  }

  /**
   * Convert Cartesian coordinates into a one-dimensional value
   * e.g.:
   * | 0 0 0 |
   * | 0 0 0 |
   * | 0 1 0 |
   * The value 1 is located at position (3, 2), the 8th value,
   * corresponding to the index of 7 for a one-dimensional array. Note that this
   * assumes a square grid
   * @param i the row
   * @param j the column
   * @return the one-dimensional array index for the grid position
   */
  private int convertCoordinates(int i, int j) {
    return (n * (i - 1) + j) - 1;
  }

  /**
   * Method used to determine whether a particular node is open
   * 
   * @param i the index in the one-dimensional array
   * @return true if the node is open, false otherwise
   */
  private boolean isOpen(int i) {
    return grid[i];
  }

  /**
   * Method used to connect the given node to the one above it, when possible
   * @param p the position of the node
   */
  private void unionAbove(int p) {
    if (p >= n) {
      if (isOpen(p - n)) {
        uf.union(p, p - n);
        ufBackwash.union(p, p - n);
      }
    } else {
      uf.union(p, n * n);
      ufBackwash.union(p, n * n);
    }
  }

  /**
   * Method used to connect the given node to the one below it, when possible
   * 
   * @param p
   *          the position of the node
   */
  private void unionBelow(int p) {
    if (p < n * (n - 1)) {
      if (isOpen(p + n)) {
        uf.union(p, p + n);
        ufBackwash.union(p, p + n);
      }
    } else
      uf.union(p, n * n + 1);
  }

  /**
   * Method used to connect the given node to the one to its left, when possible
   * 
   * @param p
   *          the position of the node
   */
  private void unionLeft(int p) {
    if (p % n != 0) {
      if (isOpen(p - 1)) {
        uf.union(p, p - 1);
        ufBackwash.union(p, p - 1);
      }
    }
  }

  /**
   * Method used to connect the given node to the one to its right, when
   * possible
   * 
   * @param p
   *          the position of the node
   */
  private void unionRight(int p) {
    if ((p + 1) % n != 0) {
      if (isOpen(p + 1)) {
        uf.union(p, p + 1);
        ufBackwash.union(p, p + 1);
      }
    }
  }

  public static void main(String[] args) {
    Percolation p = new Percolation(3);

    p.open(1, 1);
    p.open(2, 1);
    // p.open(2, 2);
    // p.open(3, 2);
    p.open(1, 3);
    p.open(2, 3);
    p.open(3, 3);

    System.out.println(p);
    System.out.println("Does the system percolate? " + p.percolates());

    System.out.println("Is the (1, 1) node full? " + p.isFull(1, 1));
    System.out.println("Is the (2, 2) node full? " + p.isFull(2, 2));
    System.out.println("Is the (2, 1) node full? " + p.isFull(2, 1));
    System.out.println("Is the (3, 3) node full? " + p.isFull(3, 3));
    
    System.out.println("\n\nNew Percolation Grid:");
    
    p = new Percolation(10);
    
    p.open(1, 1);
    p.open(2, 1);
    p.open(3, 1);
    p.open(4, 1);
    p.open(5, 1);
    p.open(6, 1);
    p.open(7, 1);
    p.open(8, 1);
    p.open(9, 1);
    p.open(10, 1);
    p.open(10, 3);
    
    System.out.println("\n\nIs the (1, 1) node open? " + p.isOpen(1, 1));
    System.out.println("Is the (2, 1) node full? " + p.isFull(2, 1));
    System.out.println("Is the (3, 1) node full? " + p.isFull(3, 1));
    System.out.println("Is the (10, 3) node full? " + p.isFull(10, 3));
  }
}
