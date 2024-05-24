3import java.util.Comparator;
import java.util.HashMap;

public class Worksheet10Stuff {
    private abstract class Vertex {
        abstract LinkedList<Edge> edgesOut();
    }

    private abstract class Edge {
        abstract Vertex other(Vertex v);

        abstract double distance();
    }

    LinkedList<Vertex> vertices;

    /**
     * Returns the shortest path (of Edges) from Vertex a to Vertex b
     * or null if no such path exists.
     */
    public LinkedList<Edge> BFS(Vertex a, Vertex b) {
        // the Queue of Vertices to visit
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(a);

        // a HashMap we'll maintain for trace-back purposes
        // edgeTo.get(v) will be the edge we used to get to v
        HashMap<Vertex, Edge> edgeTo = new HashMap<>();

        while (queue.size() > 0) {
            // the next Vertex to visit
            Vertex cur = queue.poll();

            // for each edge leaving cur
            for (Edge edge : cur.edgesOut()) {
                // get the other endpoint of e from cur
                Vertex next = edge.other(cur);

                // if I haven't visited that vertex before
                if (!edgeTo.containsKey(next)) {
                    // add it to the queue
                    queue.offer(next);

                    // record how I got there in edgeTo
                    edgeTo.put(next, edge);

                    if (next.equals(b))
                        return traceback(b, edgeTo);
                }
            }
        }

        // if I never found b, just return null
        return null;
    }

    private LinkedList<Edge> traceback(Vertex v, HashMap<Vertex, Edge> edgeMap) {
        // the ArrayList we'll return at the end of this method
        LinkedList<Edge> out = new LinkedList<>();

        // cur will track where we are in our traceback
        Vertex cur = v;

        // traceback until we get back to start
        while (edgeMap.containsKey(cur)) {
            // get the edge we used to get to next
            Edge e = edgeMap.get(cur);

            // add the edge to the solution
            out.addFirst(e);

            // work our way backward
            cur = e.other(cur);
        }

        return out;
    }

    /**
     * Returns the distance to every Vertex from the given starting Vertex
     */
    public HashMap<Vertex, Double> dijkstra(Vertex start) {
        // A HashMap to help us with the PriorityQueue
        HashMap<Vertex, Double> distanceFromStart = new HashMap<>();
        for (Vertex v : vertices)
            distanceFromStart.put(v, Double.POSITIVE_INFINITY);
        distanceFromStart.put(start, 0.0);

        // The comparator to help us sort our PriorityQueue
        Comparator<Vertex> comparator = new Comparator<>() {
            public int compare(Vertex a, Vertex b) {
                return (int) (distanceFromStart.get(a) - distanceFromStart.get(b));
            }
        };

        // the PriorityQueue of Vertices to visit
        PriorityQueue<Vertex> pQueue = new Heap<>(comparator);
        for (Vertex v : vertices)
            pQueue.offer(v);

        while (pQueue.size() > 0) {
            // the next Vertex to visit
            Vertex cur = pQueue.poll();
            double curDist = distanceFromStart.get(cur);

            // for each edge leaving cur
            for (Edge edge : cur.edgesOut()) {
                // get the other endpoint of e from cur
                Vertex next = edge.other(cur);
                double nextDist = distanceFromStart.get(next);

                double newDist = curDist + edge.distance();

                if (newDist < nextDist) {
                    distanceFromStart.put(next, newDist);
                    pQueue.updatePriority(next);
                }
            }
        }

        return distanceFromStart;
    }

    public static void main(String[] args) {
        double inf = Double.POSITIVE_INFINITY;
        System.out.println((int) inf); // returns Integer.MAX_VALUE
        System.out.println((int) (inf - inf)); // returns 0
    }
}
