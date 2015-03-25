
/**
 * Represents the typical 8-puzzle board
 */
public class Board {
  
  /**
   * Basic board constructor
   * @param blocks a 2-dimensional array where the
   * first index represents rows and the second
   * columns
   */
  public Board(int[][] blocks) {
    
  }
  
  /**
   * @return the Board's dimension.
   */
  public int dimension() {
    return 0;
  }
  
  /**
   * Computes the value for the Hamming priority function
   * It represents the number of blocks that are currently
   * out of place.
   * @return the number of blocks that are out of place
   */
  public int hamming() {
    return 0;
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
    return false;
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
    return false;
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
    return null;
  }
  
  public static void main(String[] args) {
    System.out.println("COMPILES!");
  }
}
