import java.util.ArrayList;

public class FindBridges {
    private UnweightedGraph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;

    private ArrayList<Edge> res;

    public FindBridges(UnweightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        res = new ArrayList<>();

        for (int v = 0; v < G.V(); v++){
            if (!visited[v])
                dfs(v, v);
        }
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;

        for (int w: G.adj(v)){
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                //v-w is bridge
                if (low[w] > ord[v])
                    res.add(new Edge(v, w));
            }
            else if (w != parent){
               low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g2.txt");
        FindBridges bridges = new FindBridges(G);

        System.out.println(bridges.result());
    }
}