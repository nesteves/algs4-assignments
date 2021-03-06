

/**
 * Represents the typical 8-puzzle board
 */
public class Board {
  
  private final int[][] blocks;
  private final int n;
  
  /**
   * Basic board constructor
   * @param blocks a 2-dimensional array where the
   * first index represents rows and the second
   * columns
   */
  public Board(int[][] blocks) {
    // Check for null input
    if (blocks == null) throw new java.lang.NullPointerException("Null argument invalid.");
    // Guarantee that the board is square
    for (int i = 0; i < blocks.length; i++) {
      if (blocks.length != blocks[i].length)
        throw new java.lang.IllegalArgumentException("The board must be square - have the same number of rows and columns.");
    }
    
    this.blocks = new int[blocks.length][blocks.length];
    
    for (int i = 0; i < blocks.length; i++) {
      for (int j = 0; j < blocks[i].length; j++) {
        this.blocks[i][j] = blocks[i][j];
      }
    }
    
    this.n = blocks.length;
  }
  
  /**
   * @return the Board's dimension.
   */
  public int dimension() {
    return this.n;
  }
  
  /**
   * Computes the value for the Hamming priority function
   * It represents the number of blocks that are currently
   * out of place.
   * @return the number of blocks that are out of place
   */
  public int hamming() {
    int result = 0;
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (blocks[i][j] != 0 && blocks[i][j] != goalValue(i, j))
          result++;
      }
    }
    
    return result;
  }
  
  /** 
   * Computes the value for the Manhattan priority function
   * It is the sum of the Manhattan distances from the blocks
   * to their goal positions (vertical + horizontal)
   * @return the sum of horizontal and vertical distances
   * from each block to its goal position
   */
  public int manhattan() {
    int result = 0;
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (blocks[i][j] != 0)
          result += Math.abs(i - valueGoalRow(blocks[i][j])) + Math.abs(j - valueGoalColumn(blocks[i][j]));
      }
    }
    
    return result;
  }
  
  /**
   * Checks whether this board is the goal board
   * @return true if the instance is the goal board,
   * i. e., if all blocks are in their goal positions and 
   * false otherwise
   */
  public boolean isGoal() {
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (blocks[i][j] != goalValue(i, j))
          return false;
      }
    }
    
    return true;
  }
  
  /**
   * Defines Board that are obtainable by exchanging adjacent
   * blocks in the same row
   * @return a Board that results from exchanging 2 adjacent
   * blocks in the same row
   */
  public Board twin() {
    int[][] twinArr = new int[n][n];
    boolean valuesSwapped = false;
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (!valuesSwapped && j > 0 && blocks[i][j-1] != 0 && blocks[i][j] != 0) {
          twinArr[i][j-1] = blocks[i][j];
          twinArr[i][j] = blocks[i][j-1];
          valuesSwapped = true;
        }
        else {
          twinArr[i][j] = blocks[i][j];
        }
      }
    }
    
    return new Board(twinArr);
  }
  
  /**
   * Compares 2 Boards for equality
   * @param that the other Board
   * @return true if both boards are identical (same blocks
   * in the same positions), false otherwise
   */
  public boolean equals(Object x) {
    
    if (this == x) return true;
    if (x == null) return false;
    if (this.getClass() != x.getClass()) return false;
    
    Board that = (Board) x;
    
    if (this.dimension() != that.dimension()) return false;
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (this.blocks[i][j] != that.blocks[i][j])
          return false;
      }
    }
    
    return true;
  }
  
  /**
   * Creates a collection of all neighboring Boards
   * @return all the neighbors of the current instance
   */
  public Iterable<Board> neighbors() {
    Queue<Board> availableMoves = new Queue<Board>();
    
    int zeroRow = 0;
    int zeroCol = 0;
    int[][] referenceBlocks = new int[n][n];
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        referenceBlocks[i][j] = blocks[i][j];
        if (blocks[i][j] == 0) {
          zeroRow = i;
          zeroCol = j;
        }
      }
    }
    
    // Vertical neighbors
    if (zeroRow > 0 && zeroRow < n - 1) {
      availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow - 1, zeroCol));
      availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow + 1, zeroCol));
    }
    else {
      if (zeroRow == 0) {
        // neighbor below
        availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow + 1, zeroCol));
      }
      else {
        // neighbor above
        availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow - 1, zeroCol));
      }
    }
    
    // Horizontal neighbors
    if (zeroCol > 0 && zeroCol < n - 1) {
      availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow, zeroCol - 1));
      availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow, zeroCol + 1));
    }
    else {
      if (zeroCol == 0) {
        // neighbor to the right
        availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow, zeroCol + 1));
      }
      else {
        // neighbor to the left
        availableMoves.enqueue(getNeighbor(referenceBlocks, zeroRow, zeroCol, zeroRow, zeroCol - 1));
      }
    }
    
    return availableMoves;
  }
  
  /**
   * Produces a String representation of the current instance
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    
    str.append(n);
    for (int i = 0; i < n; i++) {
      str.append("\n");
      for (int j = 0; j < n; j++) {
        str.append(" ").append(blocks[i][j]);
      }
    }
    
    return str.toString();
  }
  
  /**
   * Returns the goal value, given the board position
   * @param row the value's row
   * @param col the value's column
   * @return the goal value for the board
   */
  private int goalValue(int row, int col) {
    if (row + 1 != this.n || col + 1 != this.n) {
      return (n * row) + col + 1;
    }
    return 0;
  }
  
  /**
   * Computes the row where the given value should
   * be on a goal board
   * @param value the value for which the goal row
   * is to be found
   * @return the goal row for the given value
   */
  private int valueGoalRow(int value) {
    if (value < 1 || value > this.n * this.n) throw new java.lang.IllegalArgumentException("The given value should be on the board.");
    
    return (value - 1) / this.n;
  }
  
  /**
   * Computes the column where the given value should
   * be on a goal board
   * @param value the value for which the goal column
   * is to be found
   * @return the goal column for the given value
   */
  private int valueGoalColumn(int value) {
    if (value < 1 || value > this.n * this.n) throw new java.lang.IllegalArgumentException("The given value should be on the board.");
    
    return (value - 1) % this.n;
  }
  
  /**
   * Switches 2 values in a 2-dimensional array, builds a new Board, resets the original array
   * and returns the newly created Board
   * @param givenArray the array on which to perform the switch
   * @param rowA the row index of the first value
   * @param colA the column index of the first value
   * @param rowB the row index of the second value
   * @param colB colA the column index of the second value
   */
  private Board getNeighbor(int[][] givenArray, int rowA, int colA, int rowB, int colB) {
    int aux = givenArray[rowA][colA];
    givenArray[rowA][colA] = givenArray[rowB][colB];
    givenArray[rowB][colB] = aux;
    
    Board neighbor = new Board(givenArray);
    
    givenArray[rowB][colB] = givenArray[rowA][colA];
    givenArray[rowA][colA] = aux;
    
    return neighbor;
  }
  
  public static void main(String[] args) {
    
    Board testBoard = new Board(new int[][] {
        {8,1,3},
        {4,0,2},
        {7,6,5}
    });
    
    System.out.println("Test private method goalValue()");
    System.out.println("The goal value for position [0][0] is " + testBoard.goalValue(0,0));
    System.out.println("The goal value for position [1][1] is " + testBoard.goalValue(1,1));
    System.out.println("The goal value for position [1][2] is " + testBoard.goalValue(1,2));
    System.out.println("The goal value for position [2][2] is " + testBoard.goalValue(2,2));
    
    System.out.println("\nTest the hamming() function:");
    System.out.println("The hamming value for the board:");
    System.out.println("\t8 1 3 \n\t4   2\n\t7 6 5");
    System.out.println("The overall Hamming distance for the board is: " +  testBoard.hamming());
    
    System.out.println("\nTest the toString() function:");
    System.out.println(testBoard);
  }
}
