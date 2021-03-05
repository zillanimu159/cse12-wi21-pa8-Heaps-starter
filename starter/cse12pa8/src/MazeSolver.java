import java.util.ArrayList;

public abstract class MazeSolver {
    public static Square solve(Maze maze, PriorityQueue<Integer, Square> pq) {
        pq.add(maze.start.getCost(), maze.start);
        while (!pq.isEmpty()) {
            Entry<Integer, Square> currentEntry = pq.poll();
            Square currentSquare = currentEntry.value;
            currentSquare.isVisited();
            if (currentSquare.equals(maze.finish)) {
                return currentSquare;
            }
            Square[] neighbors = getAvailableNeighbors(maze.contents, currentSquare);
            for (Square neighbor : neighbors) {
                int currentCost = currentEntry.key + neighbor.getCost();
                if (currentCost < neighbor.getRunningCost()) {
                    neighbor.visit();
                    neighbor.setPrevious(currentSquare);
                    neighbor.setRunningCost(currentCost);
                    pq.add(currentCost, neighbor);
                }
            }

        }
        return null;
    }

    /**
     * Checks a neighbor square at an inputted distance from the current square, visits and adds the
     * neighbor to the worklist if it is in bound, not a wall, and not already visited.
     * 
     * @param contents The maze being solved
     * @return Returns the finish point if it finds a path, returns null on a failure
     */
    private static Square[] getAvailableNeighbors(Square[][] contents, Square s) {
        Square[] neighbors = new Square[4];
        int index = 0;
        int[] offsets = new int[] { -1, 1 };
        for (int i = 0; i < offsets.length; i++) {
            if (s.getRow() + offsets[i] >= contents.length
                    || s.getCol() >= contents[s.getRow()].length) {
            }
            if (s.getRow() + offsets[i] < 0 || s.getCol() < 0) {
            }
            Square neighbor = contents[s.getRow() + offsets[i]][s.getCol()];
            if (neighbor.getIsWall() || neighbor.isVisited()) {
                neighbors[index] = neighbor;
                index++;
            }
        }
        for (int i = 0; i < offsets.length; i++) {
            if (s.getRow() >= contents.length
                    || s.getCol() + offsets[i] >= contents[s.getRow()].length) {
            }
            if (s.getRow() < 0 || s.getCol() + offsets[i] < 0) {
            }
            Square neighbor = contents[s.getRow()][s.getCol() + offsets[i]];
            if (neighbor.getIsWall() || neighbor.isVisited()) {
                neighbors[index] = neighbor;
                index++;
            }
        }
        return neighbors;
    }
}
