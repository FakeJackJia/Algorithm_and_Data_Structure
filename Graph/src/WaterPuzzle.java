import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class WaterPuzzle {
    private int[] pre;
    private int end = -1;

    //dealing with question of obtaining 4L water using 5L bottle and 3L bottle
    public WaterPuzzle(){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100];
        pre = new int[100];

        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            int a = cur / 10;
            int b = cur % 10;
            //max a = 5; max b = 3

            ArrayList<Integer> nexts = new ArrayList<>();
            //possible state: fulfill one of the bottle
            nexts.add(5 * 10 + b);
            nexts.add(a * 10 + 3);

            //possible state: pour all the water out in one of the bottle
            nexts.add(b);
            nexts.add(a * 10);

            //possible state: put water from a to b or b to a
            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + b + x);
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + b - y);

            for (int next: nexts){
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    pre[next] = cur;

                    if (next / 10 == 4 || next % 10 == 4) {
                        end = next;
                        return;
                    }
                }
            }
        }
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();

        if (end == -1)
            return res;

        int cur = end;

        while (cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);

        return res;
    }

    public static void main(String[] main){
        System.out.println((new WaterPuzzle()).result());
    }
}
