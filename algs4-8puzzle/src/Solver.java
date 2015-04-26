import java.util.Comparator;

/**
 * Instantiates and solves a Board
 */
public class Solver {
  
  private Stack<Board> moveSequence = new Stack<Board>();
  
  /**
   * Represents a game tree search node
   */
  private class SearchNode {
    
    SearchNode previousNode;
    Board boardPosition;
    int totalMoves;
    
    public SearchNode(SearchNode previous, Board position, int moves) {
      this.previousNode = previous;
      this.boardPosition = position;
      this.totalMoves = moves;
    }
  }
  
  /**
   * Provides a method to compare 2 different search nodes in respect
   * to the manhattan distance the boards they represent have
   */
  private static class SearchNodeComparator implements Comparator<SearchNode> {
    public int compare(SearchNode n1, SearchNode n2) {
      int mDist1 = n1.boardPosition.manhattan();
      int mDist2 = n2.boardPosition.manhattan();
      
      if (mDist1 > mDist2) {
        return 1;
      }
      else if (mDist1 == mDist2) {
        return 0;
      }
      else {
        return -1;
      }
    }
  }
  
  /**
   * Finds a solution to the given Board
   * using the A* search algorithm
   * @param initial the initial Board
   */
  public Solver(Board initial) {
    
    SearchNode mainNode = new SearchNode(null, initial, 0);
    MinPQ<SearchNode> mainPQ = new MinPQ<SearchNode>(new SearchNodeComparator());
    mainPQ.insert(mainNode);
    
    SearchNode twinNode = new SearchNode(null, initial.twin(), 0);
    MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>(new SearchNodeComparator());
    twinPQ.insert(twinNode);
    
    while (!mainNode.boardPosition.isGoal() && !twinNode.boardPosition.isGoal()) {
      mainNode = processQueue(mainPQ);
      twinNode = processQueue(twinPQ);
    }
    
    if (mainNode.boardPosition.isGoal())
      buildMoveSequence(mainNode);
  }
  
  /**
   * @return true if the initial Board is 
   * solvable, false otherwise
   */
  public boolean isSolvable() {
    return moveSequence.size() > 0;
  }
  
  /**
   * Determines the minimum number of moves
   * required to solve the initial Board  
   * @return the minimum number of moves to solve
   * the initial Board, -1 if it is unsolvable
   */
  public int moves() {
    return moveSequence.size() - 1;
  }
  
  /**
   * @return the collection of Boards representing
   * the sequence of least moves to solve the initial
   * Board, null if unsolvable
   */
  public Iterable<Board> solution() {
    return moveSequence;
  }
  
  /**
   * Takes the best available move from the queue and inserts the next moves
   * @param nodeQueue the MinPQ of available moves
   * @return the selected move
   */
  private SearchNode processQueue(MinPQ<SearchNode> nodeQueue) {
    SearchNode nextMove = (SearchNode) nodeQueue.delMin();
    
    for (Board b : nextMove.boardPosition.neighbors()) {
      if (nextMove.previousNode == null || !b.equals(nextMove.previousNode.boardPosition)) {
        nodeQueue.insert(new SearchNode(nextMove, b, nextMove.totalMoves++));
      }
    }
    
    return nextMove;
  }
  
  /**
   * Takes the search node representing the final
   * Board position builds the sequence of moves
   * @param finalPosition
   */
  private void buildMoveSequence(SearchNode finalPosition) {
    SearchNode currentNode = finalPosition;
    
    while (currentNode != null) {
      moveSequence.push(currentNode.boardPosition);
      currentNode = currentNode.previousNode;
    }
  }
  
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}
