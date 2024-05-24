import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;

public class HeapTest {
    public static void test(int n) {
        PriorityQueue<Double> test = new Heap<Double>(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return o1.compareTo(o2);
                }
            });
        double[] control = new double[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            control[i] = rand.nextDouble();
            test.offer(control[i]);
        }
        Arrays.sort(control);
        // System.out.println(Arrays.toString(control));

        for(int i = 0; i < control.length ; i++) {
            // System.out.println("size"+ test.size());
            // System.out.println("root" + test.peek());
            // System.out.println(control[i]);
            if (test.size() == 0 || !test.peek().equals(control[i]) || !test.poll().equals(control[i]))
                System.out.println("\tERROR for n == " + n + " after removing " + (n - i) + " items.");
        }
    }

    public static void main(String[] args){
        int [] heapSizes = { 3,20,1000000 };
        for(int heapSize : heapSizes ) {
            System.out.println( "Testing heap with " + heapSize + " items:" );
            test( heapSize );
        }
    }
}