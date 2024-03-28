import java.util.HashSet;

public class FindCutPoints {
    private UnweightedGraph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;

    //using hashset because the cut point may have multiple adjacent points
    //thus the cut point may be added more than once
    private HashSet<Integer> res;

    public FindCutPoints(UnweightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        res = new HashSet<>();

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

        int child = 0;
        for (int w: G.adj(v)){
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                //v is a cut point
                if (v != parent && low[w] >= ord[v])
                    res.add(v);

                child++;

                //special case for the root
                if (v == parent && child > 1)
                    res.add(v);
            }
            else if (w != parent){
                low[v] = Math.min(low[v], ord[w]);
            }
        }
    }

    public HashSet<Integer> result(){
        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g2.txt");
        FindCutPoints cutPoints = new FindCutPoints(G);

        System.out.println(cutPoints.result());
    }
}