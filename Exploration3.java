public class Exploration3 {
    public static void main(String[] args) {
        int mazeSize = 20;
        double obstacleDensity = 0.2;
        boolean display = false;
        int delay = 0;

        int totalTrials = 1000;

        double avgCellsExploredDFS = conductDFSExperiment(mazeSize, obstacleDensity, totalTrials, display, delay);
        double avgCellsExploredBFS = conductBFSExperiment(mazeSize, obstacleDensity, totalTrials, display, delay);
        double avgCellsExploredAStar = conductAStarExperiment(mazeSize, obstacleDensity, totalTrials, display, delay);

        // Output the results
        System.out.println("Average Cells Explored (DFS): " + avgCellsExploredDFS);
        System.out.println("Average Cells Explored (BFS): " + avgCellsExploredBFS);
        System.out.println("Average Cells Explored (A*): " + avgCellsExploredAStar);
    }

    private static double conductDFSExperiment(int mazeSize, double obstacleDensity, int totalTrials, boolean display, int delay) {
        int totalCellsExplored = 0;

        for (int i = 0; i < totalTrials; i++) {
            Maze maze = new Maze(mazeSize, mazeSize, obstacleDensity);
            Cell start = maze.get(0, 0);
            Cell target = maze.get(mazeSize - 1, mazeSize - 1);

            AbstractMazeSearch dfs = new MazeDepthFirstSearch(maze);
            dfs.search(start, target, display, delay);

            for (Cell cell : maze) {
                if (cell.getPrev() != null) {
                    totalCellsExplored++;
                }
            } 
        }       
            

        return (double) totalCellsExplored / totalTrials;
    }
    

    private static double conductBFSExperiment(int mazeSize, double obstacleDensity, int totalTrials, boolean display, int delay) {
        int totalCellsExplored = 0;

        for (int i = 0; i < totalTrials; i++) {
            Maze maze = new Maze(mazeSize, mazeSize, obstacleDensity);
            Cell start = maze.get(0, 0);
            Cell target = maze.get(mazeSize - 1, mazeSize - 1);

            AbstractMazeSearch bfs = new MazeBreadthFirstSearch(maze);
            bfs.search(start, target, display, delay);
            
            for (Cell cell : maze) {
                if (cell.getPrev() != null) {
                    totalCellsExplored++;
                }
            }
            
        }
        
        return (double) totalCellsExplored / totalTrials;
        
    }

    private static double conductAStarExperiment(int mazeSize, double obstacleDensity, int totalTrials, boolean display, int delay) {
        int totalCellsExplored = 0;

        for (int i = 0; i < totalTrials; i++) {
            Maze maze = new Maze(mazeSize, mazeSize, obstacleDensity);
            Cell start = maze.get(0, 0);
            Cell target = maze.get(mazeSize - 1, mazeSize - 1);

            AbstractMazeSearch astar = new MazeAStarSearch(maze);
            astar.search(start, target, display, delay);

            for (Cell cell : maze) {
                if (cell.getPrev() != null) {
                    totalCellsExplored++;
                }
            } 
        }       
            

        return (double) totalCellsExplored / totalTrials;
    }
}
