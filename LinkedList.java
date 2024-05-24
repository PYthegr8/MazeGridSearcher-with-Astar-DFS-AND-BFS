/*
 * Author: Papa Yaw Owusu Nti
 * Date: March 11th, 2024
 * Class: CS 231 B
 * Project: Project 3
 * Description: This program implements a linkedlist with its methods and iterators
*/

import java.util.Iterator;    // defines the Iterator interface
import java.util.Objects;


public class LinkedList<T> implements Stack<T>,Queue<T>,Iterable<T>{

    private static class Node<T> {
        
        private Node<T> next;
        private T item;

        //  a constructor that initializes next to null and the container field to item.
        public Node(T item) {
            this.next = null ;
            this.item = item;
        }
        // returns the value of the container field.
        public T getData() {
            return this.item ;

        }
        // sets next to the given node.
        public void setNext(Node <T> n) {
            this.next = n;

        }

        // returns the next field.
        public Node<T> getNext() {
            return this.next;

        }

       

    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // constructor that initializes the fields so it is an empty list.
    public LinkedList() {
        this.head = null;
        this.tail =null;
        this.size = 0;
        
    }


    // returns the size of the list.
    public int size() {
        return this.size;
    }


    // empties the list (resets the fields so it is an empty list)
    public void clear() {
        this.head = null;
        this.tail =null;
        this.size = 0;
    }


    // returns true if the list is empty, otherwise this method returns false.
    public boolean isEmpty() {
        return size == 0;
    }


    // returns a String representation of the list.
    public String toString() {
        String res = "" ;
        Node<T> temp = head;
        if (isEmpty()){
            return null;
        }
        while (temp.next != null){
            
            res += String.valueOf(temp.getData()) + " --> ";
            temp = temp.next;
        }
        return res;
    }

    // returns the item on the top of the stack
    public T peek(){
        if (isEmpty()){
            return null;
        }
        return get(0) ;
        
    }


    // removes and returns the item on the top of the stack
    public T pop(){
        if (isEmpty()){
            return null;
        }
    
        T top = remove();
        return top;
    }


    // adds the given item to the top of the stack
    public void push(T item){
        add(item);
    }


     /**
     * Adds the given {@code item} to the end of this queue.
     * 
     * @param item the item to add to the queue.
     */
    public void offer(T item){
        addLast(item);
    }

    /**
     * Returns and removes the item at the front of the queue.
     * @return the item at the front of the queue.
     */
    public T poll(){
        return remove();
    }

    // inserts the item at the end of the list.
    public void addLast(T item){
        if (isEmpty()){
            Node<T> new_node = new Node<T>(item);
            this.head = new_node;
            this.tail = new_node;
        }
        else {
            Node<T> new_node = new Node<T>(item);
            this.tail.next = new_node;
            this.tail = new_node;
        }
        this.size++;
    }

    // removes the lastitem at the end of the list.
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        Node<T> temp = head;
        Node<T> removedItem;

        if (size == 1) {
            removedItem = head;
            head = null;
            tail = null;}

        while (temp.next!= tail){
            temp = temp.getNext();
        }

        removedItem = temp.getNext();
        removedItem.setNext(null);
        tail = temp;
        size--;
        return removedItem.getData();

    }

    // gets the last item at the end of the list.
    public T getLast(){
        return tail.getData();
    }



    // // inserts the item at the beginning of the list.
    public void add(T item){
        
        if (isEmpty()){
            Node<T> new_node = new Node<T>(item);
            this.head = new_node;
            this.tail = new_node;
        }
        Node<T> new_node = new Node<T>(item);
        new_node.next = this.head;
        this.head = new_node;
        this.size++;

    }
    // inserts the item at the specified position in the list.
    public void add(int index, T item) {
        Node<T> newNode = new Node<>(item);
        Node<T> temp = head;
        
        if (index < 0 || index > size) {
            return;
        } 

        if (index == size) {
            addLast(item); 
            return;
        }

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }

            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            
        }
        size++;
    }

    public boolean contains(Object o) {
        Node<T> temp = head;

        while (temp.next !=null){
            if (temp.getData().equals(o)){
                return true;
            } 
            temp = temp.getNext();
        }
        return false;
    }

    // returns the item specified by the given index.
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }

        if (index == size - 1) {
           return getLast(); 
        }
        
        Node<T> temp = head;

        for (int i = 0; i< index; i++){
            temp = temp.getNext();
        } return temp.getData();
    } 

    //  removes the first item of the list and returns it.
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = head.getData();   
        head = head.getNext();

        size--;
        if (isEmpty()) {
            this.head = null;
            this.tail = null ;
        }
        return removedItem;
    }
    // removes the item at the specified position in the list and returns it.
    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return remove(); // Call the remove() method for removing the first element
        }
        if (index == size) {
            return removeLast(); 
        }
      
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        T removedItem = temp.getNext().getData();
        temp.setNext(temp.getNext().getNext());
        size--;
        return removedItem;
    }

    public boolean equals(Object o){

    if (!(o instanceof LinkedList)) {
        return false;
    }

    // If I have reached this line, o must be a LinkedList
    LinkedList<?> otherList = (LinkedList<?>) o;
    if (size != otherList.size) {
        return false;
    }
    
    Node<T> thisTemp = head;
    Node<?> otherTemp = otherList.head;

    while (thisTemp != null) {
        if (!Objects.equals(thisTemp.getData(), otherTemp.getData())) {
            return false;
        }
        thisTemp = thisTemp.getNext();
        otherTemp = otherTemp.getNext();
    }
    return true;
    
   


    }
    
    private class LLIterator implements Iterator<T> {

    // the constructor for the LLIterator given the head of a list. 
    private Node<T> current_node;
    public LLIterator(Node<T> head){
        this.current_node = head;

    }

    // returns true if there are still values to traverse (if the current node reference is not null).
    public boolean hasNext() {
        return this.current_node != null;
    }

    /*returns the next item in the list, which is the item contained in the current node. 
    The method also needs to move the traversal along to the next node in the list. */
    public T next() {
            T data = current_node.getData();
            current_node = current_node.getNext(); // Move to the next node
            return data;
        }

    //  does nothing. Implementing this function is optional for an Iterator.
    public void remove(){}

    
}
// Return a new LLIterator pointing to the head of the list
public Iterator<T> iterator() {
    return new LLIterator( this.head );
    }

}