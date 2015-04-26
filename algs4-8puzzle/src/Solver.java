
/**
 * Instantiates and solves a Board
 */
public class Solver {
  
  /**
   * Represents a game tree search node
   */
  private class searchNode {
    
    searchNode previousNode;
    Board boardPosition;
    int totalMoves;
    
    public searchNode(searchNode previous, Board position, int moves) {
      this.previousNode = previous;
      this.boardPosition = position;
      this.totalMoves = moves;
    }
  }
  
  /**
   * Finds a solution to the given Board
   * using the A* search algorithm
   * @param initial the initial Board
   */
  public Solver(Board initial) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  /**
   * @return true if the initial Board is 
   * solvable, false otherwise
   */
  public boolean isSolvable() {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  /**
   * Determines the minimum number of moves
   * required to solve the initial Board  
   * @return the minimum number of moves to solve
   * the initial Board, -1 if it is unsolvable
   */
  public int moves() {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  /**
   * @return the collection of Boards representing
   * the sequence of least moves to solve the initial
   * Board, null if unsolvable
   */
  public Iterable<Board> solution() {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  public static void main(String[] args) {
    System.out.println("COMPILES!");
  }
}
