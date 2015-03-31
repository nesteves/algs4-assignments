import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {

  @Test
  public void testBoardCorrectInput() {
    int[][] testInput = new int[][]{
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    assertEquals("After creating a new 3*3 board, the resulting dimension should be 3.",
        testInput.length, testBoard.dimension());
  }
  
  @Test(expected = NullPointerException.class)
  public void testBoardNullInput() {
    new Board(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testBoardNonSquareInput() {
    new Board(new int[][] { {1,2}, {1} });
  }
  
  @Test
  public void testToString() {
    int[][] testInput = new int[][]{
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    String expected = "3\n 1 2 3\n 4 5 6\n 7 8 0";
    
    assertEquals("The output of toString() should be correct.",
        testBoard.toString(), expected);
  }
  
  @Test
  public void testIsGoalTrue() {
    int[][] testInput = new int[][]{
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    assertTrue(testBoard.isGoal());
  }
  
  @Test
  public void testIsGoalFalse() {
    int[][] testInput = new int[][]{
        {3,2,1},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    assertFalse(testBoard.isGoal());
  }
}
