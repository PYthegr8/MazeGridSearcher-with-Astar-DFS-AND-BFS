/*
 * Author: Papa Yaw Owusu Nti
 * Date: April 27th, 2024
 * Class: CS 231 B
 * Project: Project 7
 * Description: This program tests the functionalities of the HMaze Search and ensures the landscae displays as expected
*/
public class Main {
    public static void main(String[] args) {
    Maze maze = new Maze(20, 20, 0.2);
    System.out.println("Initial Maze:");
    System.out.println(maze);

    Cell start = maze.get(0, 0);
    Cell target = maze.get(15, 15);


    // Run this for extension1
    // AbstractMazeSearch short_path = new ShortestPathSearch(maze);
    // LinkedList<Cell> path = short_path.search(start, target, true, 10);

    // Run this for extension2
    AbstractMazeSearch diagshort_path = new ShortestPathSearch(maze);
    LinkedList<Cell> path= diagshort_path.shortest_search(start, target, true, 10);

    System.out.println("Path found:");
    if (path != null) {
        System.out.println(path);
    } else {
        System.out.println("No path found.");
    }
}

}
