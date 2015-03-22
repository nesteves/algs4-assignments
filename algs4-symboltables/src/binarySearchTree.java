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
    public Node left, right;
    
    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }
  
  /**
   * Adds a key-value pair to the binary tree
   * @param key
   * @param value
   */
  public void put(Key key, Value value) {
    
  }
  
  /**
   * Returns the value associated with the given key
   * @param key given key
   * @return the value associated with the given key
   */
  public Value get(Key key) {
    return null;
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
}
