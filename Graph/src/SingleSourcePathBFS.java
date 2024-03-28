import java.util.*;

public class SingleSourcePathBFS {

    private UnweightedGraph G;
    private boolean[] visited;
    private int s;
    private int[] pre;
    private int[] dis;

    //would generate the shortest path from source to target (for unweighted graph)
    public SingleSourcePathBFS(UnweightedGraph G, int s){
        G.validateVertex(s);

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];

        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);

        bfs(s);
    }

    private void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;

        while (!queue.isEmpty()){
            int v= queue.poll();

            for (int w: G.adj(v)){
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
            }
        }
    }

    public boolean isConnected(int t){
        G.validateVertex(t);

        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();

        if (!isConnected(t))
            return res;

        int cur = t;

        while (cur != s){
            res.add(cur);

            cur = pre[cur];
        }
        res.add(cur);

        Collections.reverse(res);
        return res;
    }

    //return the shortest distance from source to target
    public int dis(int t){
        G.validateVertex(t);

        return dis[t];
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        SingleSourcePathBFS singleSourcePathBFS = new SingleSourcePathBFS(G, 0);

        System.out.println(singleSourcePathBFS.path(6));
        System.out.println(singleSourcePathBFS.dis(6));
    }
}