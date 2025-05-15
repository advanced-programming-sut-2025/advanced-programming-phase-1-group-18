package Model;

import java.util.*;

public class AStarPathfinder {

    private static final int[][] DIRS = {
            {0, 1},  // Right
            {1, 0},  // Down
            {0, -1}, // Left
            {-1, 0}  // Up
    };

    // Helper function to get direction
    public static String getDirection(int[] from, int[] to) {
        if (to[0] > from[0]) return "DOWN";
        if (to[0] < from[0]) return "UP";
        if (to[1] > from[1]) return "RIGHT";
        return "LEFT";
    }

    public static class Natigeh {
        public List<int[]> path;
        public int turnCount;
        public int tileCount;
        public List<Turn> turns;

        public Natigeh(List<int[]> path, int turnCount, int tileCount, List<Turn> turns) {
            this.path = path;
            this.turnCount = turnCount;
            this.tileCount = tileCount;
            this.turns = turns;
        }
    }

    public static class Turn {
        public int x;
        public int y;
        public String direction;

        public Turn(int x, int y, String direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    public static Natigeh findPath(ArrayList<ArrayList<Kashi>> map, int startX, int startY, int goalX, int goalY) {
        int rows = map.size();
        int cols = map.get(0).size();

        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> cameFrom = new HashMap<>();
        Map<String, String> directionFrom = new HashMap<>();
        Set<String> visited = new HashSet<>();
        List<Turn> turns = new ArrayList<>();

        queue.add(new int[]{startX, startY});
        visited.add(startX + "," + startY);
        cameFrom.put(startX + "," + startY, null);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == goalX && y == goalY) {
                // Reconstruct the path
                List<int[]> path = new ArrayList<>();
                while (cameFrom.get(x + "," + y) != null) {
                    path.add(new int[]{x, y});
                    String direction = directionFrom.get(x + "," + y);
                    if (path.size() > 1) {
                        int[] prev = path.get(path.size() - 2);
                        String prevDirection = directionFrom.get(prev[0] + "," + prev[1]);
                        // Check if direction changed
                        if (!prevDirection.equals(direction)) {
                            turns.add(new Turn(x, y, direction));
                        }
                    }
                    String prevKey = x + "," + y;
                    String parentKey = cameFrom.get(prevKey)[0] + "," + cameFrom.get(prevKey)[1];
                    x = cameFrom.get(prevKey)[0];
                    y = cameFrom.get(prevKey)[1];
                }

                Collections.reverse(path);
                Collections.reverse(turns);

                return new Natigeh(path, turns.size(), path.size(), turns);
            }

            for (int[] dir : DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols) continue;
                if (map.get(nx).get(ny).getWalkable() == null || !map.get(nx).get(ny).getWalkable())
                    continue;  // Check if the tile is walkable

                String neighborKey = nx + "," + ny;
                if (!visited.contains(neighborKey)) {
                    visited.add(neighborKey);
                    queue.add(new int[]{nx, ny});
                    cameFrom.put(neighborKey, current);
                    directionFrom.put(neighborKey, getDirection(current, new int[]{nx, ny}));
                }
            }
        }

        return null;  // No path found
    }

    public static int calculatePower(int tileCount, int turnCount) {
        return (tileCount + 10 * turnCount) / 20;
    }

}
