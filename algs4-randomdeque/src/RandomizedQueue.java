import java.util.Iterator;

/**
 * 
 * @author nunoe
 * Implements a queue that returns elements in a random order
 * 
 * @param <Item> generic implementation, accepts any type of object
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
  
  private Item[] q;
  private int totalItems;
  
  /**
   * Initialize the RandomizedQueue
   */
  @SuppressWarnings("unchecked") // suppresses the warning on the unchecked cast from Object to <Item>
  public RandomizedQueue() {
    q = (Item[]) new Object[1];
    totalItems = 0;
  }
  
  public boolean isEmpty() {
    return totalItems == 0;
  }
  
  public int size() {
    return totalItems;
  }
  
  public void enqueue(Item item) {
    if (item == null) throw new java.lang.NullPointerException();
    
    totalItems++;
  }
  
  public Item dequeue() {
    if (totalItems == 0) throw new java.util.NoSuchElementException();
    
    totalItems--;
    
    return null;
  }
  
  public Item sample() {
    if (totalItems == 0) throw new java.util.NoSuchElementException();
    
    return null;
  }
  
  public Iterator<Item> iterator() {
    return null;
  }
  
  public static void main(String[] args) {
    
    RandomizedQueue<Integer> testRQueue = new RandomizedQueue<Integer>();
    
    System.out.println("Is the queue empty? " + testRQueue.isEmpty());
    testRQueue.enqueue(1);
    testRQueue.enqueue(2);
    System.out.println("Queue size after adding 2 items: " + testRQueue.size());
    System.out.println("Is the queue empty? " + testRQueue.isEmpty());
    testRQueue.dequeue();
    System.out.println("Queue size after removing 1 item: " + testRQueue.size());
    testRQueue .dequeue();
    System.out.println("Is the queue empty after removing the last item? " + testRQueue.isEmpty());
    
  }
  
  
}
