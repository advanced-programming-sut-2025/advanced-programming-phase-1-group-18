package Model;

import java.util.*;

public class AStarPathfinder {

    private static final int[][] DIRS = {
            {0, 1},   // راست
            {1, 0},   // پایین
            {0, -1},  // چپ
            {-1, 0}   // بالا
    };


    public static class Node implements Comparable<Node> {
        public int x;
        public int y;
        int g, h;
        Node parent;

        public Node(int x, int y, int g, int h, Node parent) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }

        public int f() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f(), other.f());
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node n = (Node) o;
            return this.x == n.x && this.y == n.y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }

    public static class Result {
        public List<Node> path;
        public int turnCount;
        public int tileCount;

        public Result(List<Node> path, int turnCount, int tileCount) {
            this.path = path;
            this.turnCount = turnCount;
            this.tileCount = tileCount;
        }
    }

    public static Result findPath(ArrayList<ArrayList<Kashi>> map, int startX, int startY, int goalX, int goalY) {
        int rows = map.size();
        int cols = map.get(0).size();

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        Node start = new Node(startX, startY, 0, heuristic(startX, startY, goalX, goalY), null);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();


            if (current.x == goalX && current.y == goalY) {
                List<Node> path = reconstructPath(current);
                int turns = countTurns(path);
                return new Result(path, turns, path.size());
            }

            closedSet.add(current);

            for (int[] dir : DIRS) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols) continue;


                if (!map.get(nx).get(ny).getWalkable()) continue;

                Node neighbor = new Node(nx, ny, current.g + 1, heuristic(nx, ny, goalX, goalY), current);
                if (closedSet.contains(neighbor)) continue;

                openSet.add(neighbor);
            }
        }

        return null;
    }

    private static int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        int tileCount = 0;

        while (node != null) {
            path.add(node);
            tileCount++;
            node = node.parent;
        }

        Collections.reverse(path);

        return path;
    }

    private static int countTurns(List<Node> path) {
        if (path.size() < 3) return 0;

        int turns = 0;
        for (int i = 2; i < path.size(); i++) {
            Node prev = path.get(i - 2);
            Node curr = path.get(i - 1);
            Node next = path.get(i);

            int dx1 = curr.x - prev.x;
            int dy1 = curr.y - prev.y;
            int dx2 = next.x - curr.x;
            int dy2 = next.y - curr.y;

            if (dx1 != dx2 || dy1 != dy2) {
                turns++;
            }
        }
        return turns;
    }

    public static int CalculateEnergy(int tiles, int turns) {
        int Energy = (10 * turns + tiles) / 20;
        return Energy;
    }
}
