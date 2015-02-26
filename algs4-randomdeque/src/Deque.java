import java.util.Iterator;

/**
 * 
 * Implementation of a double-ended queue - generalizes a stack and
 * a queue, supporting add and remove operations at the front and
 * back.
 *
 * @param <Item> Generic type. Allows the use of the Deque with
 * any type
 */
public class Deque<Item> implements Iterable<Item> {
  
  Node<Item> first;
  Node<Item> last;
  int size = 0;
  
  public Deque() {
    first = null;
    last = null;
  }
  
  public boolean isEmpty() {
    return first == null;
  }
  
  public int size() {
    return 0;
  }
  
  public void addFirst(Item item) {
    if (item == null) throw new java.util.NoSuchElementException();
    
    Node<Item> newNode = new Node<Item>();
    newNode.value = item;
    newNode.next = first;
    newNode.previous = null;
    first = newNode;
    
    size++;
  }
  
  public void addLast(Item item) {
    if (item == null) throw new java.util.NoSuchElementException();
  }
  
  public Item removeFirst() {
    return null;
  }
  
  public Item removeLast() {
    return null;
  }
  
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>(first);
  }
  
  /**
   * Class used to reference elements in the Deque
   *
   * @param <Item> Generic type
   */
  private static class Node<Item> {
    private Item value;
    private Node<Item> next;
    private Node<Item> previous;
  }
  
  /**
   * Iterator implementation for the class Deque
   *
   * @param <E> Generic type
   */
  public class DequeIterator<Item> implements Iterator<Item> {
    
    private Node<Item> current;
    
    public DequeIterator(Node<Item> first) {
      current = first;
    }
    
    public boolean hasNext() {
      return current != null;
    }
    
    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();
      
      Item value = current.value;
      current = current.next;
      return value;
    }
    
    public void remove() { throw new java.lang.UnsupportedOperationException(); }
  }
  
  public static void main(String[] args) {
    Deque<Integer> testDq = new Deque<Integer>();
    
    testDq.addFirst(1);
    testDq.addFirst(2);
    System.out.println(testDq.size());
    
    System.out.println("\nPrint all the Deque elements:\n");
    for (int i : testDq) {
      System.out.print(" " + i);
    }
  }
}
