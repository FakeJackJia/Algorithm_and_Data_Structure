import java.util.ArrayList;
import java.util.Collections;

public class HamiltonPath {
    private UnweightedGraph G;
    private boolean[] visited;
    private int[] pre;
    private int end;
    private int s;

    //O(n!)
    //find a hamilton path starting at s
    public HamiltonPath(UnweightedGraph G, int s){
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;

        dfs(s, s, G.V());
    }

    //variable left represents the number of vertices that have not yet been visited
    private boolean dfs(int v, int parent, int left){
        visited[v] = true;
        pre[v] = parent;
        left--;

        if (left == 0){
            end = v;
            return true;
        }

        for (int w: G.adj(v)){
            if (!visited[w]) {
                if (dfs(w, v, left))
                    return true;
            }
        }

        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();

        if (end == -1)
            return res;

        int cur = end;

        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);

        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g4.txt");
        HamiltonPath hamiltonPath = new HamiltonPath(G, 0);

        System.out.println(hamiltonPath.result());
    }
}