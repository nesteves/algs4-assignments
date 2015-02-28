import java.util.Iterator;

/**
 * 
 * Implementation of a double-ended queue - generalizes a stack and
 * a queue, supporting add and remove operations at the front and
 * back.
 * 
 * @author Nuno Esteves
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
    return size;
  }
  
  public void addFirst(Item item) {
    if (item == null) throw new java.util.NoSuchElementException();
    
    Node<Item> newNode = new Node<Item>();
    newNode.value = item;
    newNode.next = first;
    newNode.previous = null;
    first = newNode;
    
    if (size == 0) last = newNode;
    else newNode.next.previous = newNode;
    size++;
  }
  
  public void addLast(Item item) {
    if (item == null) throw new java.util.NoSuchElementException();
    
    Node<Item> newNode = new Node<Item>();
    newNode.value = item;
    newNode.next = null;
    newNode.previous = last;
    last = newNode;
    
    if (size == 0) first = newNode;
    else newNode.previous.next = newNode;
    
    size++;
  }
  
  /**
   * Pops the value in front of the queue
   * @return the item in front of the queue
   */
  public Item removeFirst() {
    Item returnVal = first.value;
    
    first = first.next;
    first.previous = null;
    size--;
    
    return returnVal;
  }
  
  /**
   * Pops the value at the back of the queue
   * @return the item at the back of the queue
   */
  public Item removeLast() {
    Item returnVal = last.value;
    
    last = last.previous;
    last.next = null;
    size--;
    
    return returnVal;
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
  
  
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>(first);
  }
  
  /**
   * Iterator implementation for the class Deque
   *
   * @param <E> Generic type
   */
  public class DequeIterator<Item> implements Iterator<Item> {
    
    private Node<Item> current;
    
    public DequeIterator(Node<Item> first) { current = first; }
    
    public boolean hasNext() { return current != null; }
    
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
    
    // Push items
    testDq.addFirst(1);
    testDq.addFirst(2);
    testDq.addLast(0);
    testDq.addFirst(5);
    testDq.addFirst(8);
    testDq.addLast(9);
    testDq.addLast(77);
    System.out.println(testDq.size());
    System.out.println("\nPrint all the Deque elements:");
    for (int i : testDq) {
      System.out.print(" " + i + " ");
    }
    
    // Pop items
    testDq.removeFirst();
    testDq.removeLast();
    testDq.removeLast();
    testDq.removeLast();
    //testDq.removeLast();
    System.out.println("\n\n"+ testDq.size());
    System.out.println("\nPrint all the Deque elements:");
    for (int i : testDq) {
      System.out.print(" " + i);
    }
  }
}
