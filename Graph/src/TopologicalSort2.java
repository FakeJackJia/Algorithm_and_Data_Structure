import java.util.ArrayList;
import java.util.Collections;

public class TopologicalSort2 {

    private UnweightedGraph G;
    private ArrayList<Integer> res;
    private boolean hasCycle = false;

    //unable to detect cycle in directed graph
    //thus, need the helper method
    public TopologicalSort2(UnweightedGraph G){
        if (!G.isDirected())
            throw new IllegalArgumentException("Only works in directed graph");

        this.G = G;

        res = new ArrayList<>();
        hasCycle = (new CycleDetectionDirected(G)).hasCycle();
        if (hasCycle)
            return;

        GraphDFS dfs = new GraphDFS(G);
        for (int v: dfs.postOrder())
            res.add(v);

        Collections.reverse(res);
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public ArrayList<Integer> result(){
        return res;
    }

    public static void main(String[] main){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/gd.txt", true);
        TopologicalSort2 topologicalSort2 = new TopologicalSort2(G);
        System.out.println(topologicalSort2.result());
    }
}