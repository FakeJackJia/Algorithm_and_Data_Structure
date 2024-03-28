import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BellmanFord {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean hasNegativeCircle = false;
    private int[] pre;

    //O(V*E)
    public BellmanFord(WeightedGraph G, int s){
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        pre = new int[G.V()];

        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(pre, -1);

        dis[s] = 0;
        pre[s] = s;

        for (int pass = 1; pass < G.V(); pass++){
            for (int v = 0; v < G.V(); v++){
                for (int w: G.adj(v)){
                    //edge relaxation
                    if(dis[v] != Integer.MAX_VALUE && dis[v] + G.getWeight(v, w) < dis[w]) {
                        dis[w] = dis[v] + G.getWeight(v, w);
                        pre[w] = v;
                    }
                }
            }
        }

        //use Vth pass to check whether the graph has a negative circle
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                //edge relaxation
                if (dis[v] != Integer.MAX_VALUE && dis[v] + G.getWeight(v, w) < dis[w])
                    hasNegativeCircle = true;
            }
        }
    }

    public boolean hasNegCycle(){
        return hasNegativeCircle;
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);

        return dis[v] != Integer.MAX_VALUE;
    }

    public int disTo(int v){
        G.validateVertex(v);
        if (hasNegativeCircle)
            throw new RuntimeException("Negative circle exits");

        return dis[v];
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
        res.add(cur);

        Collections.reverse(res);

        return res;
    }

    public static void main(String[] main){
        WeightedGraph G = new WeightedGraph("./GraphFile/gw1.txt");
        BellmanFord bellmanFord = new BellmanFord(G, 0);
        if (!bellmanFord.hasNegCycle()){
            for (int v = 0; v < G.V(); v++)
                System.out.print(bellmanFord.disTo(v) + " ");
        }
        else
            System.out.println("has negative circle");

        System.out.println();
        System.out.println(bellmanFord.path(3));
    }

}
