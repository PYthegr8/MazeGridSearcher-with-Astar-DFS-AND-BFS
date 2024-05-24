/*
 * Author: Papa Yaw Owusu Nti
 * Date: April 27th, 2024
 * Class: CS 231 B
 * Project: Project 7
 * Description: This program  defines the basic operations of the Heap Data Structure implemented as an ArrayList. 
                Has getters and setters as well as BubbleUP and BubbleDown methods for reheapifying. 
                It includes methods for adding, removing, and updating priority of items in the heap. 
*/

import java.util.Comparator;
import java.util.ArrayList;

public class Heap<T> implements PriorityQueue<T> {



    private Comparator<T> comparator;
    private ArrayList<T> heap; 
    private int size;



    /**
     * Constructs a new Heap with the specified comparator and whether it should be a max heap or not.
     * If the comparator is null, natural ordering will be used.
     * 
     * @param comparator the comparator to determine the order of the heap.
     * @param maxHeap true if the heap should be a max heap, false if it should be a min heap.
     */

     
    public Heap(Comparator<T> comparator, boolean maxHeap) {
        this.size = 0;
        if (comparator == null) {
            this.comparator = new Comparator<T>() {
                @SuppressWarnings("unchecked")
                @Override
                public int compare(T o1, T o2) {
                    return ((Comparable<T>) o1).compareTo(o2);
                }
            };
        } else {
            this.comparator = comparator;
        }

        if (maxHeap) {
            this.comparator = this.comparator.reversed();
            // if (maxHeap) {
    
                //             Comparator<T> reverseComparator = new Comparator<T>(){
                //                 @Override
                //                 public int compare(T o1, T o2) {
                //                     return comparator.compare(o2,o1);
                //                 }
                //             };
                //             this.comparator = reverseComparator;
        }

        this.heap = new ArrayList<>();
    }

    public Heap(Comparator<T> comparator) {
        this(comparator, false);
    }



    /**
     * Swaps the elements at the two specified indices.
     * 
     * @param idx1 the index of the first element.
     * @param idx2 the index of the second element.
     */

    private void swap(int idx1, int idx2) {
        T temp = heap.get(idx1);
        heap.set(idx1, heap.get(idx2));
        heap.set(idx2, temp);
    }


    /**
     * Returns the parent index of the given index.
     * 
     * @param idx the index to find the parent of.
     * @return the parent index of the given index.
     */

    private int getParentIdx(int idx) {
        return (idx - 1) / 2;
    }


    /**
     * Returns the left child index of the given index.
     * 
     * @param idx the index to find the left child of.
     * @return the left child index of the given index.
     */

    private int getLeftChildIdx(int idx) {
        return 2 * idx + 1;
    }

    /**
     * Returns the right child index of the given index.
     * 
     * @param idx the index to find the right child of.
     * @return the right child index of the given index.
     */

    private int getRightChildIdx(int idx) {
        return 2 * idx + 2;
    }



     /**
     * Moves the element at the specified index up in the heap to maintain heap property.
     * 
     * @param idx the index of the element to move up.
     */

    private void bubbleUp(int idx) {
        if (idx == 0) return; // Root node

        int parentIdx = getParentIdx(idx);

        if (comparator.compare(heap.get(idx), heap.get(parentIdx)) < 0) {
            swap(idx, parentIdx);
            bubbleUp(parentIdx);
        }
    }




    /**
     * Moves the element at the specified index down in the heap to maintain heap property.
     * 
     * @param idx the index of the element to move down.
     */

    private void bubbleDown(int idx) {
        int leftIdx = getLeftChildIdx(idx);
        int rightIdx = getRightChildIdx(idx);
        int largest = idx;

        if (leftIdx < size && comparator.compare(heap.get(leftIdx), heap.get(largest)) < 0) {
            largest = leftIdx;
        }
        if (rightIdx < size && comparator.compare(heap.get(rightIdx), heap.get(largest)) < 0) {
            largest = rightIdx;
        }

        if (largest != idx) {
            swap(idx, largest);
            bubbleDown(largest);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    @Override
    public void offer(T item) {
        heap.add(item);
        size++;
        bubbleUp(size - 1);
    }

    @Override
    public T poll() {
        if (heap.isEmpty()) return null;

        T root = heap.get(0);
        heap.set(0, heap.get(size - 1));
        heap.remove(size - 1);
        size--;

        if (size > 0) {
            bubbleDown(0);
        }

        return root;
    }



    /**
     * Updates the priority of the given item in the heap.
     * Assumes all other items' priorities in this heap have not changed.
     * 
     * @param item the item whose priority has been updated.
     */
    @Override
    public void updatePriority(T item) {
        // Linear search to find the item
        for (int i = 0; i < size; i++) {
            if (heap.get(i).equals(item)) {
                // After finding the item, perform bubble up and bubble down
                bubbleUp(i);
                bubbleDown(i);
                return;
            }
        }
    }

    @Override
    public String toString() {
        int depth = 0;
        return toString(0, depth);
    }

    private String toString(int idx, int depth) {
        if (idx >= size) {
            return "";
        }
        String left = toString(getLeftChildIdx(idx), depth + 1);
        String right = toString(getRightChildIdx(idx), depth + 1);

        String myself = "\t".repeat(depth) + heap.get(idx) + "\n";
        return right + myself + left;
    }
}
