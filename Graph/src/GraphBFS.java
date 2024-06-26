import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    private UnweightedGraph G;
    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();

    //O(V + E)
    public GraphBFS(UnweightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++){
            if (!visited[v])
                bfs(v);
        }
    }

    private void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()){
            int v= queue.poll();
            order.add(v);

            for (int w: G.adj(v)){
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }


    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        GraphBFS graphBFS = new GraphBFS(G);

        System.out.println(graphBFS.order());
    }
}