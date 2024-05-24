/*
 * Papa Yaw Owusu Nti
 * 22nd March 2024
 * CS231 B
 * Project 4
 * 
 * Description: This interface defines the basic operations of a queue data structure.
                * - offer(T item): Adds the given item to the end of the queue.
                * - size(): Returns the number of items in the queue.
                * - peek(): Returns the item at the front of the queue without removing it.
                * - poll(): Returns and removes the item at the front of the queue.
 */

public interface Queue<T> {

    /**
     * Adds the given {@code item} to the end of this queue.
     * 
     * @param item the item to add to the queue.
     */
    public void offer(T item);

    /**
     * Returns the number of items in the queue.
     * @return the number of items in the queue.
     */
    public int size();

    /**
     * Returns the item at the front of the queue.
     * @return the item at the front of the queue.
     */
    public T peek();

    /**
     * Returns and removes the item at the front of the queue.
     * @return the item at the front of the queue.
     */
    public T poll();
}
