/*
 * Author: Papa Yaw Owusu Nti
 * Date: April 27th, 2024
 * Class: CS 231 B
 * Project: Project 7
 * Description: this class will extend the AbstractMazeSearch class and implement the DFS algorithm
*/

public class MazeDepthFirstSearch extends AbstractMazeSearch  {

    private Stack<Cell> stack;

    public MazeDepthFirstSearch(Maze maze){
        super(maze);
        this.stack = new LinkedList<>();
    }

    // this method returns the next Cell to explore.
    public Cell findNextCell(){
        return stack.pop();
    }


    // this method adds the given Cell to whatever structure is storing the future Cells to explore.
    public void addCell(Cell next){
        stack.push(next);
    }


    // this method returns the number of future Cells to explore (so just the size of whatever structure is storing the future Cells).
    public int numRemainingCells(){
        return stack.size();
    }

}
