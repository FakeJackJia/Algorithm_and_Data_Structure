import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//O(V + E)
public class TopologicalSort {

    private UnweightedGraph G;
    private ArrayList<Integer> res;
    private boolean hasCycle = false;

    //has ability to detect cycle in directed graph
    public TopologicalSort(UnweightedGraph G){
        if (!G.isDirected())
            throw new IllegalArgumentException("Only works in directed graph");

        this.G = G;

        res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        int[] indegrees = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegrees[v] = G.indegree(v);
            if (indegrees[v] == 0)
                queue.add(v);
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();
            res.add(cur);

            for (int next: G.adj(cur)){
                indegrees[next]--;
                if (indegrees[next] == 0)
                    queue.add(next);
            }
        }

        if (res.size() != G.V()) {
            hasCycle = true;
            res.clear();
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public ArrayList<Integer> result(){
        return res;
    }

    public static void main(String[] main){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/gd.txt", true);
        TopologicalSort topologicalSort = new TopologicalSort(G);
        System.out.println(topologicalSort.result());
    }
}