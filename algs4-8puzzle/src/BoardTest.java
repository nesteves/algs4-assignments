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
        expected, testBoard.toString());
  }
  
  @Test
  public void testIsGoalTrue() {
    int[][] testInput = new int[][]{
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    assertTrue("This should be a solved board.", testBoard.isGoal());
  }
  
  @Test
  public void testIsGoalFalse() {
    int[][] testInput = new int[][]{
        {3,2,1},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    assertFalse("This should not be a solved board.", testBoard.isGoal());
  }
  
  @Test
  public void testEqualsTrue() {
    int[][] testInput = new int[][]{
        {3,2,1},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    int[][] testInput2 = new int[][]{
        {3,2,1},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard2 = new Board(testInput2);
    
    assertTrue("The 2 boards should be equal.", testBoard.equals(testBoard2));
  }
  
  @Test
  public void testEqualsFalse() {
    int[][] testInput = new int[][]{
        {3,2,1},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard = new Board(testInput);
    
    int[][] testInput2 = new int[][]{
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };
    
    Board testBoard2 = new Board(testInput2);
    
    assertFalse("The 2 boards should be different.", testBoard.equals(testBoard2));
  }
  
  @Test
  public void testHammingDistance() {
    int[][] testInput = new int[][]{
        {3,2,1},
        {4,6,5},
        {8,7,0}
    };
    
    Board testBoard = new Board(testInput);
    int expected = 6;
    
    assertEquals("The hamming distance for the given board should be " + expected + ".", expected, testBoard.hamming());
    
    int[][] testInput2 = new int[][]{
        {8,1,3},
        {4,0,2},
        {7,6,5}
    };
    
    Board testBoard2 = new Board(testInput2);
    int expected2 = 5;
    
    assertEquals("The hamming distance for the given board should be " + expected2 + ".", expected2, testBoard2.hamming());
  }
  
  @Test
  public void testManhattanDistance() {
    int[][] testInput = new int[][]{
        {3,2,1},
        {4,6,5},
        {8,7,0}
    };
    
    Board testBoard = new Board(testInput);
    int expected = 8;
    
    assertEquals("The manhattan distance for the given board should be " + expected + ".", expected, testBoard.manhattan());
    
    int[][] testInput2 = new int[][]{
        {1,2,6},
        {8,5,3},
        {7,4,0}
    };
    
    Board testBoard2 = new Board(testInput2);
    int expected2 = 6;
    
    assertEquals("The manhattan distance for the given board should be " + expected2 + ".", expected2, testBoard2.manhattan());
    
    int[][] testInput3 = new int[][]{
        {8,1,3},
        {4,0,2},
        {7,6,5}
    };
    
    Board testBoard3 = new Board(testInput3);
    int expected3 = 10;
    
    assertEquals("The manhattan distance for the given board should be " + expected3 + ".", expected3, testBoard3.manhattan());
  }
}
