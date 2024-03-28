import java.util.ArrayList;
import java.util.Arrays;

public class ConnectedComponent {
    private Graph G;
    private int[] visited;
    private int ccCount = 0;

    public ConnectedComponent(Graph G) {
        this.G = G;
        visited = new int[G.V()];

        Arrays.fill(visited, -1);

        //considering graph may have several connected components
        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, ccCount);
                ccCount++;
            }
        }
    }

    private void dfs(int v, int ccID) {
        visited[v] = ccID;

        for (int w : G.adj(v)) {
            if (visited[w] == -1)
                dfs(w, ccID);
        }
    }

    public int count(){
        return ccCount;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[ccCount];

        for (int i = 0; i < ccCount; i++)
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);

        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        ConnectedComponent cc = new ConnectedComponent(G);

        System.out.println(G);
        System.out.println("Number of component: " + cc.count());
        System.out.println(cc.isConnected(0, 5));

        ArrayList<Integer>[] cmp = cc.components();
        for (int i = 0; i < cmp.length; i++) {
            System.out.print("Component " + i + ": ");
            System.out.println(cmp[i]);
        }
    }
}