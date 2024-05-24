/*
 * Author: Papa Yaw Owusu Nti
 * Date: April 27th, 2024
 * Class: CS 231 B
 * Project: Project 7
 * Description: This program  defines the basic operations of searching a Maze. It includes some abstract methods
                that its children will have to implement and other general methods used (search and traceback).
*/

import java.awt.Graphics ;
import java.awt.Color;

public abstract class AbstractMazeSearch {
    
    private Maze maze;
    private Cell start;
    private Cell target;
    private Cell cur;

    public AbstractMazeSearch(Maze maze){
        this.maze = maze;
        this.cur = null;
        this.start = null;
        this.target = null;
    }


    // this method returns the next Cell to explore.
    public abstract Cell findNextCell();



    // this method adds the given Cell to whatever structure is storing the future Cells to explore.
    public abstract void addCell(Cell next);



    // this method returns the number of future Cells to explore (so just the size of whatever structure is storing the future Cells).
    public abstract int numRemainingCells();


    // just returns the underlying Maze.
    public Maze getMaze(){
        return maze;
    }



    // sets the given target to be the target of the search.
    public void setTarget(Cell target){
        this.target = target;
    }



    // returns the target of the search.
    public Cell getTarget(){
        return this.target;
    }



    // sets the given cell to be the current location of the search.
    public void setCur(Cell cell){
        this.cur = cell;
    }



    // returns the current Cell location of the search.
    public Cell getCur(){
        return this.cur;
    }



    // sets the given start to be the start of the search. Additionally, set start's prev to be itself - the reason for this will make sense a bit later.
    public void setStart(Cell start){
        this.start= start;
        start.setPrev(start);
    }


    // returns the start of the search.
    public Cell getStart(){
        return start;
    }



    // sets the current, start, and target Cells to be null.
    public void reset(){
        cur = null;
        start = null;
        target = null;
    }


    // finds a path from the start to the specified cell by repeatedly following the prev path. Returns the path if found, otherwise returns null.
    public LinkedList<Cell> traceback(Cell cell){

        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();

        while (curCell != null) {
            path.add(curCell);
            if (curCell.getRow() == start.getRow() && curCell.getCol() == start.getCol()) {
                return path;
            }
            curCell = curCell.getPrev();
        }
        return null;
    }


    public LinkedList<Cell> search(Cell start, Cell target){

        setStart(start);
        setTarget(target);
        setCur(start);

        LinkedList<Cell> cellsToExplore = new LinkedList<>();
        cellsToExplore.add(start);

        while (!cellsToExplore.isEmpty()) {
            Cell cur = findNextCell(); // Get the next cell to explore
            setCur(cur);

            for (Cell neighbor : getMaze().getNeighbors(cur)) {
                if (neighbor.getPrev() == null) { // Neighbor hasn't been visited
                    neighbor.setPrev(cur); // Set the previous cell of the neighbor to be cur
                    cellsToExplore.add(neighbor); // Add neighbor to the future cells to explore
                    if (neighbor == target) {
                        // We found the target, return the traceback path
                        return traceback(target);
                    }
                }
            }
        }

        // No path found
        return null;
    }

    
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) {

        setStart(start);
        setTarget(target);
        setCur(start);

        addCell(start);
        MazeSearchDisplay mazedisplay = null;

        if (display == true) {
            mazedisplay = new MazeSearchDisplay(this, 30);
        }

        while (numRemainingCells() != 0) {

            if (display == true) {
                try {
                    Thread.sleep(delay);
                    mazedisplay.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            setCur(findNextCell());

            for (Cell neighbor : maze.getNeighbors(cur)) {
                if (neighbor.getPrev() == null) {
                    neighbor.setPrev(cur);
                    addCell(neighbor);
                    if (neighbor.getRow() == target.getRow() && neighbor.getCol() == target.getCol()) {
                        target.setPrev(cur);
                        return traceback(neighbor);
                    }
                }
            }
        }

        return null;
    }

    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
    
        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }
}

