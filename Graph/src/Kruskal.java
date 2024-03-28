import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    //O(ElogE)
    public Kruskal(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<>();

        ConnectedComponent cc = new ConnectedComponent(G);
        if (cc.count() > 1)
            return;

        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++){
            for (int w: G.adj(v)){
                if (v < w)
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
            }
        }

        Collections.sort(edges);
        UF uf = new UF(G.V());

        for (WeightedEdge edge: edges){
            int v = edge.getV();
            int w = edge.getW();

            if (!uf.isConnected(v, w)){
                mst.add(edge);
                uf.unionElements(v, w);
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] main){
        WeightedGraph G = new WeightedGraph("./GraphFile/gw.txt");
        Kruskal kruskal = new Kruskal(G);

        System.out.println(kruskal.result());
    }
}