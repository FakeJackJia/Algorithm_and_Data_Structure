import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    //O(ElogE)
    public Prim(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<>();

        ConnectedComponent cc = new ConnectedComponent(G);
        if (cc.count() > 1)
            return;

        boolean[] visited = new boolean[G.V()];
        visited[0] = true;

        Queue<WeightedEdge> pq = new PriorityQueue<>();
        for (int w: G.adj(0)){
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));
        }

        while (!pq.isEmpty()){
            WeightedEdge minEdge = pq.remove();
            if (visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;

            mst.add(minEdge);

            int neww = minEdge.getW();
            visited[neww] = true;

            for (int w: G.adj(neww)){
                if (!visited[w])
                    pq.add(new WeightedEdge(neww, w, G.getWeight(neww, w)));
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] main){
        WeightedGraph G = new WeightedGraph("./GraphFile/gw.txt");
        Prim prim = new Prim(G);

        System.out.println(prim.result());
    }
}