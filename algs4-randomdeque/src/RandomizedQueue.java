import java.util.Iterator;

/**
 * Implements a queue that returns elements in a random order.
 * @author Nuno Esteves
 * 
 * @param <Item> generic implementation, accepts any type of object.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
  
  private Item[] q;
  private int totalItems;
  
  /**
   * Initialize the RandomizedQueue.
   */
  @SuppressWarnings("unchecked") // suppresses the warning on the unchecked cast from Object to <Item>
  public RandomizedQueue() {
    q = (Item[]) new Object[1];
    totalItems = 0;
  }
  
  /**
   * Method used to ask whether the queue is empty.
   * @return true if it is empty, false otherwise.
   */
  public boolean isEmpty() {
    return totalItems == 0;
  }
  
  /**
   * Method used to get the current size of the queue.
   * @return The current size of the queue.
   */
  public int size() {
    return totalItems;
  }
  
  /**
   * Method used to add an item to the queue.
   * @param item Item to be added to the queue.
   */
  public void enqueue(Item item) {
    if (item == null) throw new java.lang.NullPointerException();
    
    if (totalItems == q.length) resize(2 * totalItems);
    q[totalItems++] = item;
  }
  
  /**
   * Method used to remove and return a random item from
   * the queue.
   * @return a random item contained in the queue. All
   *    calls are independent.
   */
  public Item dequeue() {
    if (totalItems == 0) throw new java.util.NoSuchElementException();
    
    Item item = q[--totalItems];
    q[totalItems] = null;
    if (totalItems > 0 && totalItems == q.length / 4) resize(totalItems / 2);
    return item;
  }
  
  /**
   * Method used to get a random item from the queue.
   * @return a random item contained in the queue. All calls are
   *    independent - the same item can be returned twice in a
   *    row.
   */
  public Item sample() {
    if (totalItems == 0) throw new java.util.NoSuchElementException();
    
    return null;
  }
  
  /**
   * Resizes the array holding the queue.
   * @param newSize the new size of the queue.
   */
  private void resize(int newSize) {
    @SuppressWarnings("unchecked")
    Item[] copy = (Item[]) new Object[newSize];
    
    for (int i = 0; i < totalItems; i++)
      copy[i] = q[i];
    
    q = copy;
  }
  
  public Iterator<Item> iterator() {
    return new RQIterator<Item>();
  }
  
  /**
   * Iterator that returns elements from the RandomizedQueue
   * in random order.
   * @author Nuno Esteves
   *
   * @param <Item>
   */
  private class RQIterator<Item> implements Iterator<Item> {
    private int current;
    private int[] sequence;
    
    public RQIterator() {
      // Initialize an array where a[i] = i and shuffle the items
      sequence = new int[totalItems];
      for (int i = 0; i < totalItems; i++) 
        sequence[i] = i;
      StdRandom.shuffle(sequence);
      current = 0;
    }
    
    @Override
    public boolean hasNext() {
      return current < totalItems;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();
      
      Item value = (Item) q[sequence[current]];
      current++;
      return value;
    }
    
    @Override
    public void remove() { throw new java.lang.UnsupportedOperationException(); }
    
  }
  
  /** 
   * Basic tests for the class ensure the API works correctly.
   * @param args command-line arguments are not used.
   */
  public static void main(String[] args) {
    
    RandomizedQueue<Integer> testRQueue = new RandomizedQueue<Integer>();
    
    // Test simple operations
    System.out.println("Is the queue empty? " + testRQueue.isEmpty());
    testRQueue.enqueue(1);
    testRQueue.enqueue(2);
    System.out.println("Queue size after adding 2 items: " + testRQueue.size());
    System.out.println("Is the queue empty? " + testRQueue.isEmpty());
    testRQueue.dequeue();
    System.out.println("Queue size after removing 1 item: " + testRQueue.size());
    testRQueue .dequeue();
    System.out.println("Is the queue empty after removing the last item? " + testRQueue.isEmpty());
    
    // Test the iterator
    testRQueue.enqueue(1);
    testRQueue.enqueue(2);
    testRQueue.enqueue(3);
    testRQueue.enqueue(4);
    testRQueue.enqueue(5);
    testRQueue.enqueue(6);
    testRQueue.enqueue(7);
    testRQueue.enqueue(8);
    testRQueue.enqueue(9);
    testRQueue.enqueue(10);
    System.out.println("Print all the elements in order to test the iterator:\n");
    for (int i : testRQueue) {
      System.out.print(" " + i);
    }
    
    System.out.println();
    System.out.println("Test removing 3 items and running the iterator again.\n");
    testRQueue .dequeue();
    testRQueue .dequeue();
    testRQueue .dequeue();
    for (int i : testRQueue) {
      System.out.print(" " + i);
    }
  }
  
  
}
