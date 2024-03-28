import java.util.ArrayList;
import java.util.Stack;

public class GraphDFS {
    private UnweightedGraph G;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private boolean[] visited;

    //O(V + 2E) which is O(V + E)
    //as each edge is encountered twice
    public GraphDFS(UnweightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];

        //considering graph may have several connected components
        for (int v = 0; v < G.V(); v++){
            if (!visited[v])
                dfs(v);
        }
//        for (int v = 0; v < G.V(); v++){
//            if (!visited[v])
//                dfsLoop(v);
//        }
    }

    private void dfs(int v){
        visited[v] = true;
        pre.add(v);

        for (int w: G.adj(v)){
            if (!visited[w])
                dfs(w);
        }

        post.add(v);
    }

    private void dfsLoop(int v){
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;

        while (!stack.isEmpty()){
            int cur = stack.pop();
            pre.add(cur);

            for (int w: G.adj(cur)){
                if (!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> preOrder(){
        return pre;
    }

    public Iterable<Integer> postOrder(){
        return post;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        GraphDFS graphDFS = new GraphDFS(G);


        System.out.println(G);
        System.out.println(graphDFS.preOrder());
        System.out.println(graphDFS.postOrder());
    }
}