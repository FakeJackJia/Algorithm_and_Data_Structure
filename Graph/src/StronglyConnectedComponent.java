import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StronglyConnectedComponent {
    private UnweightedGraph G;
    private int[] visited;
    private int sccCount = 0;

    //Kosaraju's Algorithm
    //O(V + E)
    public StronglyConnectedComponent(UnweightedGraph G) {
        if (!G.isDirected())
            throw new IllegalArgumentException("SCC only works for directed graph");

        this.G = G;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);

        GraphDFS dfs =  new GraphDFS(G.reverseGraph());
        ArrayList<Integer> order = new ArrayList<>();
        for (int v: dfs.postOrder())
            order.add(v);

        Collections.reverse(order);

        for (int v: order) {
            if (visited[v] == -1) {
                dfs(v, sccCount);
                sccCount++;
            }
        }
    }

    private void dfs(int v, int sccID) {
        visited[v] = sccID;

        for (int w : G.adj(v)) {
            if (visited[w] == -1)
                dfs(w, sccID);
        }
    }

    public int count(){
        return sccCount;
    }

    public boolean isStronglyConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[sccCount];

        for (int i = 0; i < sccCount; i++)
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);

        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/gd2.txt", true);
        StronglyConnectedComponent scc = new StronglyConnectedComponent(G);

        System.out.println(G);
        System.out.println("Number of component: " + scc.count());

        ArrayList<Integer>[] cmp = scc.components();
        for (int i = 0; i < cmp.length; i++) {
            System.out.print("Component " + i + ": ");
            System.out.println(cmp[i]);
        }
    }
}
