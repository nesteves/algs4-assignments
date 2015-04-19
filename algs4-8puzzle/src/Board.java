
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
    return 0;
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
    return null;
  }
  
  /**
   * Compares 2 Boards for equality
   * @param that the other Board
   * @return true if both boards are identical (same blocks
   * in the same positions), false otherwise
   */
  public boolean equals(Board that) {
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
  public Iterable<Board> neighbours() {
    return null;
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
    
    return value / this.n;
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
    
    return value % this.n - 1;
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
