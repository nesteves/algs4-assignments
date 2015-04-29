
/**
 * Implementation of a 2-3 Tree, using a left-leaning red-black tree
 */
public class TwoThreeTree<Key extends Comparable<Key>, Value> {
  
  private static final boolean RED = true;
  private static final boolean BLACK = false;
  
  /**
   * Represents a node in the left-leaning red-black tree
   */
  private class Node {
    Key key;
    Value value;
    Node left;
    Node right;
    boolean color; 
  }
  
  public Value get(Key key) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  private Node rotateLeft(Node n) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  private Node rotateRight(Node n) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  private void flipColors(Node n) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  private boolean isRed(Node n) {
    if (n == null) return false;
    return n.color == RED;
  }
}
