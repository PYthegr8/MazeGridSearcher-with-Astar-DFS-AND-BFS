/*
 * Author: Papa Yaw Owusu Nti
 * Date: April 30th, 2024
 * Class: CS 231 B
 * Project: Project 7
 * Description: This program  defines the ShortestPathSearch algorithm
*/
import java.util.Comparator;

public class ShortestPathSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;

    public ShortestPathSearch(Maze maze) {
        super(maze);
        Comparator<Cell> comparator = new Comparator<Cell>() {

            @Override
            public int compare(Cell cell1, Cell cell2) {
                // Calculate the cost for cell1 and cell2 based on distance and acceleration assumptions
                int costCell1 = calculateCost(cell1);
                int costCell2 = calculateCost(cell2);

                // Compare the costs
                return Integer.compare(costCell1, costCell2);
            }

            private int calculateCost(Cell cell) {
                // Calculate the distance to the target
                int distance = Math.abs(getTarget().getRow() - cell.getRow())
                                + Math.abs(getTarget().getCol() - cell.getCol());

                // Calculate the cost based on acceleration assumptions
                int cost = distance; // Default cost (moving straight)
                if (cell.getPrev() != null) {
                    // If there's a previous cell, check if the movement involves a turn
                    int dx1 = cell.getRow() - cell.getPrev().getRow();
                    int dy1 = cell.getCol() - cell.getPrev().getCol();
                    int dx2 = getTarget().getRow() - cell.getRow();
                    int dy2 = getTarget().getCol() - cell.getCol();
                    // Check if turning is required (i.e., if the direction changes)
                    if (dx1 * dy2 != dy1 * dx2) {
                        // Turning is required, increase the cost
                        cost += 10; // Adjust the cost for turning
                    }
                }
                return cost;
            }

        };
        this.priorityQueue = new Heap<>(comparator, false);
    }

    @Override
    public Cell findNextCell() {
        // Return the next cell to explore
        return this.priorityQueue.poll();
    }

    @Override
    public void addCell(Cell next) {
        // Add the given cell to the priority queue
        this.priorityQueue.offer(next);
    }

    @Override
    public int numRemainingCells() {
        return this.priorityQueue.size();
    }
}

