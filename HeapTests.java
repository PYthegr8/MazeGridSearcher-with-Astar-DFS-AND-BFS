/*
 * Author: Papa Yaw Owusu Nti
 * Date: April 27th, 2024
 * Class: CS 231 B
 * Project: Project 7
 * Description: This program tests the functionalities of the Heap Data Structure
*/
import java.util.Comparator;
public class HeapTests {
    public static void main(String[] args) {
        testMaxHeap();
    }


    private static void testMaxHeap() {
        System.out.println("Testing Max Heap:");
        // Creating a max heap with a custom comparator
        PriorityQueue<Double> maxHeap = new Heap<Double>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });

        // Adding elements to the max heap
        maxHeap.offer(10.4);
        maxHeap.offer(5.6);
        maxHeap.offer(7.1);
        maxHeap.offer(3.2);
        maxHeap.offer(12.2);

        // Testing size method
        System.out.println(maxHeap.size());
        assert maxHeap.size() == 5 : "Size of max heap is incorrect";

        System.out.println(maxHeap.peek());
        // Testing peek method
        assert maxHeap.peek() == 12.2 : "Peek method returns incorrect value";


    
        // Testing poll method
        assert maxHeap.poll() == 12.2 : "Poll method returns incorrect value";
        assert maxHeap.poll() == 10.4 : "Poll method returns incorrect value";

        // Testing updatePriority method
        maxHeap.offer(15.0); // Adding an element with higher priority
        maxHeap.updatePriority(7.0); // Updating priority of an existing element
        assert maxHeap.peek() == 15.0 : "UpdatePriority method does not work correctly";

        System.out.println("All tests for Max Heap passed successfully.");
    }
}
