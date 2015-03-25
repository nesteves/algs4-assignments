
/**
 * Instantiates and solves a Board
 */
public class Solver {
  
  /**
   * Finds a solution to the given Board
   * using the A* search algorithm
   * @param initial the initial Board
   */
  public Solver(Board initial) {
    
  }
  
  /**
   * @return true if the initial Board is 
   * solvable, false otherwise
   */
  public boolean isSolvable() {
    return false;
  }
  
  /**
   * Determines the minimum number of moves
   * required to solve the initial Board  
   * @return the minimum number of moves to solve
   * the initial Board, -1 if it is unsolvable
   */
  public int moves() {
    return 0;
  }
  
  /**
   * @return the collection of Boards representing
   * the sequence of least moves to solve the initial
   * Board, null if unsolvable
   */
  public Iterable<Board> solution() {
    return null;
  }
  
  public static void main(String[] args) {
    System.out.println("COMPILES!");
  }
}
