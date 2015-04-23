import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SolverTest {
  
  Solver solvableBoardSolver;
  Solver unsolvableBoardSolver;
  
  @Before
  public void initialize() {
    solvableBoardSolver = new Solver(new Board(new int[][]{
        {0,1,3},
        {4,2,5},
        {7,8,6} 
      })
    );
    
    unsolvableBoardSolver = new Solver(new Board(new int[][]{
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,15,14,0}
      })
    );
  }
  
  
  @Test
  public void testIsSolvableTrue() {
    assertTrue("The given board should be solvable.", solvableBoardSolver.isSolvable());
  }
  
  @Test
  public void testIsSolvableFalse() {
    assertFalse("The given board should not be solvable.", unsolvableBoardSolver.isSolvable());
  }
  
  @Test
  public void testSolutionSolvable() {
    boolean goalFound = false;
    
    for (Board move : solvableBoardSolver.solution()) {
      if (move.isGoal())
        goalFound = true;
    }
    
    assertTrue("The solver didn't find a valid solution.", goalFound);
  }
  
  @Test
  public void testSolutionNotSolvable() {
    assertNull("The board is unsolvable, there should be no moves in the solution.", unsolvableBoardSolver.solution());
  }
}
