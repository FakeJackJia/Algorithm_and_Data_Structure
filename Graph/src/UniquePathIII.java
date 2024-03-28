import java.util.Arrays;

public class UniquePathIII {

    private int[][] grid;
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int R, C;
    private int start, end;
    //using technique of memoization
    private int[][] memo;

    //O(n * 2^n) where n is the number of vertices
    public int solution(int[][] grid){
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        int left = R * C;
        memo = new int[1 << (R * C)][R * C];

        for (int i = 0; i < memo.length; i++)
            Arrays.fill(memo, -1);

        for (int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                if (grid[i][j] == -1)
                    left--;
                else if (grid[i][j] == 1)
                    start = i * C + j;
                else if (grid[i][j] == 2)
                    end = i * C + j;
            }
        }

        int visited = 0;
        return dfs(visited, start, left);
    }

    private int dfs(int visited, int s, int left){
        if (memo[visited][s] != -1)
            return memo[visited][s];

        int x = s / C, y = s % C;
        visited += (1 << s);
        left--;
        int res = 0;

        if (left == 0 && s == end) {
            memo[visited][s] = 1;
            return 1;
        }

        for (int d = 0; d < 4; d++){
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];

            int n = nextX * C + nextY;

            if (inArea(nextX, nextY) && grid[nextX][nextY] != -1 && (visited & (1 << n)) == 0){
                res += dfs(visited, n, left);
            }
        }

        memo[visited][s] = res;
        return res;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && y >= 0 && x < R && y < C;
    }
}