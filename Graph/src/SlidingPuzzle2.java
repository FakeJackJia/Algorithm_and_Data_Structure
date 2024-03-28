import java.util.*;

class SlidingPuzzle2{
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int solution(int[][] board) {
        Queue<Integer> queue = new LinkedList<>();

        int initial = 100000 * board[0][0] + 10000 * board[0][1] + 1000 * board[0][2] +
                100 * board[1][0] + 10 * board[1][1] + board[1][2];

        if (initial == 123450)
            return 0;

        HashMap<Integer, Integer> visited = new HashMap<>();
        queue.add(initial);
        visited.put(initial, 0);

        while (!queue.isEmpty()){
            int cur = queue.poll();

            ArrayList<Integer> nexts = getNext(cur);

            for (int next: nexts){
                if (!visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);

                    if (next == 123450)
                        return visited.get(next);
                }
            }
        }

        return -1;
    }

    private ArrayList<Integer> getNext(int num){
        ArrayList<Integer> res = new ArrayList<>();

        int[][] cur = intToBoard(num);
        int i;

        for (i = 0; i < 6; i++){
            if (cur[i / 3][i % 3] == 0)
                break;
        }

        int x = i / 3, y = i % 3;

        for (int d = 0; d < 4; d++){
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];

            if (nextX >= 0 && nextY >= 0 && nextY < 3 && nextX < 2){
                swap(cur, x, y, nextX, nextY);
                res.add(100000 * cur[0][0] + 10000 * cur[0][1] + 1000 * cur[0][2] +
                        100 * cur[1][0] + 10 * cur[1][1] + cur[1][2]);
                swap(cur, x, y, nextX, nextY);
            }
        }

        return res;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2){
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    private int[][] intToBoard(int num){
        int[][] board = new int[2][3];

        for (int i = 5; i >= 0; i--){
            board[i / 3][i % 3] = num % 10;
            num = num / 10;
        }

        return board;
    }
}