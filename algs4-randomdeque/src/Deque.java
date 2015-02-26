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
  
  public Deque() {
    
  }
  
  public boolean isEmpty() {
    return false;
  }
  
  public int size() {
    return 0;
  }
  
  public void addFirst(Item item) {
    
  }
  
  public void addLast(Item item) {
    
  }
  
  public Item removeFirst() {
    return null;
  }
  
  public Item removeLast() {
    return null;
  }
  
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>();
  }
  
  
  /**
   * Iterator implementation for the class Deque
   *
   * @param <E> Generic type
   */
  public class DequeIterator<E> implements Iterator<E> {
    
    
    
    public boolean hasNext() {
      return false;
    }
    
    public E next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();
      return null;
    }
    
    public void remove() { throw new java.lang.UnsupportedOperationException(); }
  }
  
  
}
