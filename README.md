
## Abstract
In this project, I developed a maze solver using the search algorithms: depth-first search (DFS), breadth-first search (BFS), and A* search. 
The maze solver finds the shortest path from a start point to a target point within a maze grid. Mazes are represented using a custom Maze class, with Cells representing individual grid points.

The project comprises three main classes: Maze, Cell, and AbstractMazeSearch. The Maze class manages the maze grid and provides methods for generating random mazes, reading mazes from files, and visualizing mazes. The Cell class represents individual grid cells within the maze and contains information such as neighbors, and traversal status. The AbstractMazeSearch class serves as the base class for implementing various search algorithms and contains abstract methods(findNextCell(), addCell(), and numRemainingCells()) and properties shared among different search algorithms. 


The MazeDepthFirstSearch class implements the depth-first search algorithm. It uses a stack to explore cells in a maze, diving deep into potential paths before backtracking when no further progress can be made. Similarly, the MazeBreadthFirstSearch class implements the breadth-first search algorithm, using a queue data structure to explore cells level by level, ensuring that all cells at a particular distance from the start point are explored before moving on to cells further away. The MazeAStarSearch class implements the A* search algorithm, which uses a priority queue to prioritize cells based on their distance from the start point and estimated distance to the target.

Throughout the project, I applied object-oriented programming principles to design modular and extensible code. I implemented search algorithms using stack-based (DFS) and queue-based (BFS) approaches, as well as A* search with heuristics for improved efficiency. Error handling mechanisms were incorporated to handle invalid input and unexpected situations during maze solving. Additionally, test cases were developed to validate the functionality of each class and identify potential bugs.

![image](https://github.com/user-attachments/assets/121fd9e4-d0db-4628-8601-e6d3eebb30df)


