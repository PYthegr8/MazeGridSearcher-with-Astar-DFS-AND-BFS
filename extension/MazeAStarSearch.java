/*
 * Author: Papa Yaw Owusu Nti
 * Date: April 27th, 2024
 * Class: CS 231 B
 * Project: Project 7
 * Description: this class will extend the AbstractMazeSearch class and implement the AStar Search algorithm
*/

import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch   {

    private PriorityQueue<Cell> priorityQueue;

    public MazeAStarSearch(Maze maze){
            super(maze);
            Comparator<Cell> comparator = new Comparator<Cell>() {

            @Override
            public int compare(Cell cell1, Cell cell2) {

                // cell 1
                int numStepsCell1 = traceback(cell1).size();
                int estimatedDistance1 = Math.abs(getTarget().getRow() - cell1.getRow())
                        + Math.abs(getTarget().getCol() - cell1.getCol());

                int sumSteps1 = numStepsCell1 + estimatedDistance1;

                // cell 2
                int numStepsCell2 = traceback(cell2).size();
                int estimatedDistance2 = Math.abs(getTarget().getRow() - cell2.getRow())
                        + Math.abs(getTarget().getCol() - cell2.getCol());

                int sumSteps2 = numStepsCell2 + estimatedDistance2;

                if (sumSteps1 == sumSteps2) {
                    return 0;
                } else if (sumSteps1 < sumSteps2) {
                    return -1;
                } else {
                    return 1;
                }
            }

        };
        this.priorityQueue = new Heap<Cell>(comparator, false);
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
