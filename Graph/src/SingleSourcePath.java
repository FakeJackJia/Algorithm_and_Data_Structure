import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SingleSourcePath{
    private UnweightedGraph G;
    private int s;
    private boolean[] visited;
    private int[] pre;

    //allow check from s to any target
    public SingleSourcePath(UnweightedGraph G, int s){
        G.validateVertex(s);

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dfs(s, s);
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;

        for (int w: G.adj(v)){
            if (!visited[w])
                dfs(w, v);
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);

        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t))
            return res;

        int cur = t;
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(G, 0);
        System.out.println(singleSourcePath.isConnectedTo(6));
        System.out.println(singleSourcePath.isConnectedTo(5));

        System.out.println(singleSourcePath.path(6));
        System.out.println(singleSourcePath.path(5));
    }
}