@SuppressWarnings("unchecked")
public class ArrayList<E> {

    /**
     * This array stores the actual contents of the ArrayList
     */
    E[] arr;

    /**
     * This stores the number of items in the ArrayList (note: this is distinct from
     * the capacity)
     */
    int size;

    /**
     * This stores the index of the first item in the list. This index is not
     * necessarily 0.
     */
    int firstIndex;

    public ArrayList() {
        arr = (E[]) new Object[16];
        size = 0;

        // firstIndex is initialized to -1 to reflect the fact that there are originally
        // no contents in this array.
        firstIndex = -1;
    }

    /**
     * Returns the number of items in the ArrayList.
     * 
     * @return the number of items in the ArrayList.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the capacity of the underlying array of this ArrayList.
     * 
     * @return the capacity of the underlying array of this ArrayList.
     */
    public int capacity() {
        return arr.length;
    }

    /**
     * Allocates an array of the specified {@code newCapacity}, copies over all
     * contents of {@code arr} into this new array, and resets {@code arr} to this
     * new array.
     * 
     * @param newSize the new capacity for {@code arr}.
     */
    private void resizeArray(int newCapacity) {
        E[] newArr = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArr[i] = arr[(firstIndex + i) % capacity()];
        }

        // in the for loop above, we shift everything back to index 0.
        firstIndex = 0;

        arr = newArr;
    }

    /**
     * Returns the item at the specified {@code index}.
     * 
     * @param index the index to be inspected.
     * @return the item at the specified {@code index}.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for an array of " + size + " items.");
        }
        return arr[(firstIndex + index) % capacity()];
    }

    /**
     * This method adds the specified {@code item} into the ArrayList, setting it as
     * the first entry.
     * 
     * @param item
     */
    public void addFirst(E item) {
        if (size == capacity()){
            resizeArray(size);
        }

        firstIndex--;
        if (firstIndex < 0) {
            firstIndex = capacity() - 1;
        }

        arr[firstIndex] = item;
        size++;
    }

    /**
     * This method adds the specified {@code item} into the ArrayList, setting it as
     * the last entry.
     * 
     * @param item
     */
    public void addLast(E item) {
        if (size == capacity()) {
            resizeArray(size);
        }

        if (size == 0) {
            firstIndex = 0;
        }

        arr[(firstIndex + size) % capacity()] = item;
        size++;
    }
    

    /**
     * This method removes and returns the first entry in the ArrayList.
     * 
     * @return the first entry in the ArrayList.
     */

    public E removeFirst() {
        E toReturn = arr[firstIndex];

        arr[firstIndex] = null;

        firstIndex++;
        if (firstIndex >= capacity()) {
            firstIndex = 0;
        }

        size--;
        return toReturn;
    }
    

    /**
     * This method removes and returns the last entry in the ArrayList.
     * 
     * @return the last entry in the ArrayList.
     */
    public E removeLast() {
        E toReturn = arr[(firstIndex + size - 1) % capacity()];

        if (size == 0) {
            firstIndex = -1;
        }

        arr[(firstIndex + size - 1) % capacity()] = null;

        size--;
        return toReturn;
    }

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
            nums.addFirst(2);
            nums.addFirst(3);
            nums.addFirst(9);
            nums.addFirst(10);

            nums.addFirst(11);
            nums.addFirst(12);
            nums.addFirst(56);
            nums.addFirst(43);
            nums.addFirst(22);

            for (int i=0;i< nums.size(); i++){
            System.out.println(nums.get(i));
        }
    }
    
}

