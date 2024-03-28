import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    private int[][] grid;
    private boolean[][] visited;
    private int n;
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    private int[][] dis;

    public int solution(int[][] grid){
        this.grid = grid;

        n = grid.length;
        visited = new boolean[n][n];
        dis = new int[n][n];

        if (grid[0][0] == 1)
            return -1;

        if (n == 1)
            return 1;

        return bfs(0, 0);
    }

    private int bfs(int x, int y){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x * n + y);

        visited[x][y] = true;
        dis[x][y] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            x = cur / n;
            y = cur % n;

            for (int d = 0; d < 8; d++) {
                int nextX = x + dirs[d][0];
                int nextY = y + dirs[d][1];

                if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] == 0){
                    queue.add(nextX * n + nextY);

                    visited[nextX][nextY] = true;
                    dis[nextX][nextY] = dis[x][y] + 1;

                    if (nextX == n - 1 && nextY == n - 1)
                        return dis[nextX][nextY];
                }
            }
        }

        return -1;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}