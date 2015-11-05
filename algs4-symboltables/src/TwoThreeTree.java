
/**
 * Implementation of a 2-3 Tree, using a left-leaning red-black tree
 */
public class TwoThreeTree<Key extends Comparable<Key>, Value> {
  
  private static final boolean RED = true;
  private static final boolean BLACK = false;
  private Node root;
  
  /**
   * Represents a node in the left-leaning red-black tree
   */
  private class Node {
    Key key;
    Value value;
    int N; // total nodes in the subtree
    Node left;
    Node right;
    boolean color;
    
    Node(Key key, Value value, int N, boolean color) {
      this.key = key;
      this.value = value;
      this.N = N;
      this.color = color;
    }
  }
  
  /**
   * Used to get the value corresponding to a particular key
   * @param key
   * @return the corresponding value
   */
  public Value get(Key key) {
    Node x = root;
    while (x != null) {
      int cmp = key.compareTo(x.key);
      if (cmp < 0) x = x.left;
      if (cmp > 0) x = x.right;
      else return x.value;
    }
    return null;
  }
  
  /**
   * Method used to get the size of the
   * entire tree
   * @return the size of the entire 2-3 Tree
   */
  public int size() {
    return size(root);
  }
  
  /**
   * Method used to get the size of a
   * subtree
   * @param n root of the subtree
   * @return the size of the subtree rooted on node
   * n
   */
  public int size(Node n) {
    if (n == null) return 0;
    return n.N;
  }
  
  /**
   * Method used to insert a key-value pair
   * into the tree. If the key already exists,
   * its value is updated, otherwise the key is
   * inserted and the tree grows accordingly
   * @param key
   * @param value
   */
  public void put(Key key, Value value) {
    root = put(root, key, value);
    root.color = BLACK;
  }
  
  /**
   * Recursive method used to traverse the tree,
   * find where to insert the key-value pair and
   * perform the necessary rotations and flips
   * in order to maintain perfect red-black balance
   * @param n the current node being analyzed
   * @param key 
   * @param value
   * @return
   */
  private Node put(Node n, Key key, Value value) {
    
    // Insert the key-value pair when a null link is found
    if (n == null)
      return new Node(key, value, 1, RED);
    
    int cmp = key.compareTo(n.key);
    if (cmp < 0)
      n.left = put(n.left, key, value);
    else if (cmp > 0)
      n.right = put(n.right, key, value);
    else
      n.value = value;
    
    if (isRed(n.right) && !isRed(n.left))
      n = rotateLeft(n);
    if (isRed(n.left) && isRed(n.left.left))
      n = rotateRight(n);
    if (isRed(n.left) && isRed(n.right))
      flipColors(n);
    
    n.N = size(n.left) + size(n.right) + 1;
    return n;
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
    Node x = n.right;
    n.right = x.left;
    x.left = n;
    x.color = n.color;
    n.color = RED;
    x.N = n.N;
    n.N = 1 + size(n.left) + size(n.right);
    return x;
  }
  
  /**
   * This method mirrors rotateLeft
   * @param n the node that has a red link to its left
   * @return the node that now has a red link to its right
   */
  private Node rotateRight(Node n) {
    Node x = n.left;
    n.left = x.right;
    x.right = n;
    x.color = n.color;
    n.color = RED;
    x.N = n.N;
    n.N = 1 + size(n.left) + size(n.right);
    return x;
  }
  
  /**
   * Switches the colors of the links connected to the given node.
   * Links connecting to the child nodes are switched to RED, while
   * the link connecting to the parent node is switched to BLACK
   * @param n the node for which to switch the link colors
   */
  private void flipColors(Node n) {
    n.color = RED;
    n.left.color = BLACK;
    n.right.color = BLACK;
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
  
  /**
   * Main method used to test the class itself
   */
  public static void main(String[] args) {
    
  }
}
