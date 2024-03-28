import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Path {
    private UnweightedGraph G;
    private int s;
    private boolean[] visited;
    private int[] pre;
    private int t;

    //directly find path from s to t
    public Path(UnweightedGraph G, int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);

        this.G = G;
        this.s = s;
        this.t = t;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dfs(s, s);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;

        if (v == t)
            return true;

        for (int w : G.adj(v)) {
            if (!visited[w])
                if(dfs(w, v)) return true;
        }

        return false;
    }

    public boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected())
            return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        Path path = new Path(G, 0, 6);
        System.out.println(path.path());
    }
}