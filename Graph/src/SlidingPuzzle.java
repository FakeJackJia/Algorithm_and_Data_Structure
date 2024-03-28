import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//O(n!)
public class SlidingPuzzle {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int solution(int[][] board){
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();

        String initialState = boardToString(board);

        if (initialState.equals("123450"))
            return 0;

        queue.add(initialState);
        visited.put(initialState, 0);

        while (!queue.isEmpty()){
            String cur = queue.poll();

            ArrayList<String> nexts = getNext(cur);

            for (String next: nexts){
                if (!visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);

                    if (next.equals("123450"))
                        return visited.get(next);
                }
            }
        }

        return -1;
    }

    private ArrayList<String> getNext(String s){
        ArrayList<String> res = new ArrayList<>();

        int[][] cur = stringToBoard(s);
        int zero;

        for (zero = 0; zero < 6; zero++)
            if (cur[zero / 3][zero % 3] == 0)
                break;

        int zx = zero / 3, zy = zero % 3;

        for (int d = 0; d < 4; d++){
            int nextX = zx + dirs[d][0];
            int nextY = zy + dirs[d][1];

            if (inArea(nextX, nextY)){
                swap(cur, zx, zy, nextX, nextY);
                res.add(boardToString(cur));
                swap(cur, zx, zy, nextX, nextY);
            }
        }

        return res;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2){
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && y >= 0 && x < 2 && y < 3;
    }

    private String boardToString(int[][] borad){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j++)
                res.append(borad[i][j]);
        }

        return res.toString();
    }

    private int[][] stringToBoard(String s){
        int[][] board = new int[2][3];

        for (int i = 0; i < 6; i++)
            board[i / 3][i % 3] = s.charAt(i) - '0';

        return board;
    }
}