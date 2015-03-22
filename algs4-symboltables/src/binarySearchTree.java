/**
 * Implementation of a symbol table using an explicit binary search tree
 */
public class binarySearchTree<Key extends Comparable<Key>, Value> {
  
  private Node root;
  
  /**
   * Inner class used as a node for the binary tree
   */
  private class Node {
    private Key key;
    private Value value;
    private int count;
    public Node left, right;
    
    public Node(Key key, Value value, int count) {
      this.key = key;
      this.value = value;
      this.count = count;
    }
  }
  
  /**
   * Adds a key-value pair to the binary tree
   * @param key
   * @param value
   */
  public void put(Key key, Value value) {
    root = put(root, key, value);
  }
  
  /**
   * Returns the size of the tree
   * @return the size of the binary search tree
   */
  public int size() {
    return size(root);
  }
  
  /**
   * Returns the size of the subtree rooted at 
   * the given Node
   * @param x the given Node , treated as
   * the root of the subtree
   * @return the size of the subtree
   */
  public int size(Node x) {
    if (x == null) return 0;
    return x.count;
  }
  
  /**
   * private recursive method used to add a Node
   * to the binary tree
   * @param x
   * @param key
   * @param value
   * @return the Node it was called on
   */
  private Node put(Node x, Key key, Value value) {
    // Check for the base case of the recursion
    if (x == null) {
      return new Node(key, value, 1);
    }
    
    int cmp = key.compareTo(x.key);
    
    if (cmp < 0) {
      x.left = put(x.left, key, value);
    }
    else if (cmp > 0) {
      x.right = put(x.right, key, value);
    }
    else {
      x.value = value;
    }
    x.count = 1 + size(x.left) +size(x.right);
    return x;
  }
  
  /**
   * Returns the value associated with the given key
   * @param key given key
   * @return the value associated with the given key
   */
  public Value get(Key key) {
    Node x = root;
    
    while (x != null) {
      int cmp = key.compareTo(x.key);
      
      if (cmp < 0) {
        x = x.left;
      }
      else if (cmp > 0) {
        x = x.right;
      }
      else {
        return x.value;
      }
    }
    
    return null;
  }
  
  /**
   * Returns the smallest key in the
   * binary search tree
   * @return the smallest Key
   */
  public Key min() {
    if (root == null) return null;
    
    Node x = root;
    while (x.left != null) {
      x = x.left;
    }
    
    return x.key;
  }
  
  /**
   * Returns the largest key in the
   * binary search tree
   * @return the largest Key
   */
  public Key max() {
    if (root == null) return null;
    
    Node x = root;
    while (x.right != null) {
      x = x.right;
    }
    
    return x.key;
  }
  
  /**
   * Returns the largest key that is smaller
   * than the given key
   * @param key given key
   * @return largest key smaller than the given key
   */
  public Key floor(Key key) {
    Node x = floor(root, key);
    if (x == null) return null;
    return x.key;
  }
  
  /**
   * Recursive method that supports the floor(Key key) method
   * @param x the intermediate node
   * @param key the original given key
   */
  private Node floor(Node x, Key key) {
    if (x == null) return null;
    
    int cmp = key.compareTo(x.key);
    
    if (cmp == 0) {
      return x;
    }
    else if (cmp < 0) {
      return floor(x.left, key);
    }
    
    Node t = floor(x.right, key);
    if (t != null) return t;
    else return x;
  }
  
  /**
   * Deletes a key-value pair from the binary tree
   * @param key the key of the key-value pair to be
   * deleted
   */
  public void delete(Key key) {
    
  }
  
  /**
   * Enables the client to iterate through the keys
   * present in the binary tree
   * @return an iterator for the keys in the binary tree
   */
  public Iterable<Key> iterator() {
    return null;
  }
  
  public static void main(String[] args) {
    System.out.println("Compiles!");
  }
}