
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
  
  /**
   * Used to get the value corresponding to a particular key
   * @param key
   * @return the corresponding value
   */
  public Value get(Key key) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  /**
   * Makes the tree "lean to the left" for
   * the given node. It affects a 3-node
   * @param n he node that has a red link to its right,
   * said to be "leaning right"
   * @return the node that now has a red link to its left,
   * "leaning left"
   */
  private Node rotateLeft(Node n) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  /**
   * This method mirrors rotateLeft
   * @param n the node that has a red link to its left
   * @return the node that now has a red link to its right
   */
  private Node rotateRight(Node n) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  /**
   * Switches the colors of the links connected to the given node.
   * Links connecting to the child nodes are switched to RED, while
   * the link connecting to the parent node is switched to BLACK
   * @param n the node for which to switch the link colors
   */
  private void flipColors(Node n) {
    throw new java.lang.UnsupportedOperationException("Not implemented.");
  }
  
  /**
   * Test the color of the link to the parent
   * Node
   * @param n the node whose link to the parent
   * node is to be tested
   * @return true if the link to the parent Node is RED,
   * false otherwise
   */
  private boolean isRed(Node n) {
    if (n == null) return false;
    return n.color == RED;
  }
}
