public class Domino {

    public int solution(int n, int m, int[][] broken){
        int[][] board = new int[n][m];
        for (int[] p: broken){
            board[p[0]][p[1]] = 1;
        }

        UnweightedGraph G = new UnweightedGraph(n * m, false);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (j + 1 < m && board[i][j] == 0 && board[i][j + 1] == 0)
                    G.addEdge(i * m + j, i * m + j + 1);
                if (i + 1 < n && board[i][j] == 0 && board[i + 1][j] == 0)
                    G.addEdge(i * m + j, (i + 1) * m + j);
            }
        }

        BipartiteMatching bm = new BipartiteMatching(G);
        return bm.maxMatching();
    }
}
