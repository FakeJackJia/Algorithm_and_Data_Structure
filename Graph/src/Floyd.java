import java.util.Arrays;

public class Floyd {

    private WeightedGraph G;
    private int[][] dis;
    private boolean hasNegativeCycle = false;

    //O(V*V*V)
    public Floyd(WeightedGraph G){
        this.G = G;

        dis = new int[G.V()][G.V()];
        for (int i = 0; i < G.V(); i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);

        for (int v = 0; v < G.V(); v++){
            dis[v][v] = 0;

            for (int w: G.adj(v))
                dis[v][w] = G.getWeight(v, w);
        }

        for (int t = 0; t < G.V(); t++){
            for (int v = 0; v < G.V(); v++){
                for (int w = 0; w < G.V(); w++){
                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE && dis[v][t] + dis[t][w] < dis[v][w])
                        dis[v][w] = dis[v][t] + dis[t][w];
                }
            }
        }

        for (int v = 0; v < G.V(); v++){
            if (dis[v][v] < 0)
                hasNegativeCycle = true;
        }
    }

    public boolean hasNegCycle(){
        return hasNegativeCycle;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);

        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int disTo(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);

        return dis[v][w];
    }

    public static void main(String[] main){
        WeightedGraph G = new WeightedGraph("./GraphFile/gw1.txt");
        Floyd floyd = new Floyd(G);

        if (!floyd.hasNegCycle()){
            for (int v = 0; v < G.V(); v++){
                for (int w = 0; w < G.V(); w++){
                    System.out.print(floyd.disTo(v, w) + " ");
                }
                System.out.println();
            }
        }
        else
            System.out.println("Has negative cycle");
    }
}
