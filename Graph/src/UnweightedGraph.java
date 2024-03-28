import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Scanner;

public class UnweightedGraph implements Cloneable, Graph{
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;
    private boolean directed;
    private int[] indegrees, outdegrees;

    //O(ElogV) Space complexity: O(V + E)
    public UnweightedGraph(String filename, boolean directed){
        this.directed = directed;

        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();

            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative");

            adj = new TreeSet[V];
            for (int i = 0; i < V; i++)
                adj[i] = new TreeSet<>();

            indegrees = new int[V];
            outdegrees = new int[V];
            E = 0;

            int e = scanner.nextInt();
            if (e < 0)
                throw new IllegalArgumentException("E must be non-negative");

            for (int i = 0; i < e; i++){
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                addEdge(a, b);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public UnweightedGraph(String filename){
        this(filename, false);
    }

    public UnweightedGraph(TreeSet<Integer>[] adj, boolean directed){
        this.adj = adj;
        this.directed = directed;
        this.V = adj.length;
        this.E = 0;

        indegrees = new int[V];
        outdegrees = new int[V];

        for (int v = 0; v < V; v++){
            for (int w: adj(v)){
                outdegrees[v]++;
                indegrees[w]++;
                E++;
            }
        }

        if (!directed)
            E = E / 2;
    }

    public UnweightedGraph(int V, boolean directed){
        this.V = V;
        this.directed = directed;
        this.E = 0;

        adj = new TreeSet[V];
        for (int i = 0; i < V; i++)
            adj[i] = new TreeSet<>();
    }

    public UnweightedGraph reverseGraph(){
        TreeSet<Integer>[] rAdj = new TreeSet[V];
        for (int i = 0; i < V; i++)
            rAdj[i] = new TreeSet<>();

        for (int v = 0; v < V; v++){
            for (int w: adj(v))
                rAdj[w].add(v);
        }

        return new UnweightedGraph(rAdj, directed);
    }

    public void addEdge(int a, int b){
        validateVertex(a);
        validateVertex(b);

        if (a == b)
            throw new IllegalArgumentException("Self loop detected");
        //O(logV)
        if (adj[a].contains(b))
            throw new IllegalArgumentException("Parrallel edge detected");

        adj[a].add(b);
        if (directed){
            outdegrees[a]++;
            indegrees[b]++;
        }

        if (!directed)
            adj[b].add(a);

        E++;
    }

    public boolean isDirected(){
        return directed;
    }

    public void validateVertex(int v){
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    //O(logV)
    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    //O(degree(V))
    public Iterable<Integer> adj(int v){
        validateVertex(v);

        return adj[v];
    }

    public int indegree(int v){
        if (!directed)
            throw new RuntimeException("indegree only works for directed");

        validateVertex(v);
        return indegrees[v];
    }

    public int outdegree(int v){
        if (!directed)
            throw new RuntimeException("outdegree only works for directed");

        validateVertex(v);
        return outdegrees[v];
    }

    public int degree(int v){
        validateVertex(v);

        return adj[v].size();
    }

    public void removeEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);

        if (adj[v].contains(w)){
            E--;

            if (directed){
                outdegrees[v]--;
                indegrees[w]--;
            }
        }

        adj[v].remove(w);
        if (!directed)
            adj[w].remove(v);
    }

    @Override
    public Object clone(){
        try {
            UnweightedGraph cloned = (UnweightedGraph) super.clone();
            cloned.adj = new TreeSet[V];
            cloned.outdegrees = new int[V];
            cloned.indegrees = new int[V];

            for (int v = 0; v < V; v++){
                cloned.adj[v] = new TreeSet<>();
                for (int w: this.adj[v]){
                    cloned.adj[v].add(w);
                }

                cloned.indegrees[v] = this.indegrees[v];
                cloned.outdegrees[v] = this.outdegrees[v];
            }

            return cloned;
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w: adj[v])
                sb.append(String.format("%d ", w));
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/gd.txt", true);
        System.out.println(G);

        for (int v = 0; v < G.V(); v++)
            System.out.println(G.indegree(v) + " " + G.outdegree(v));
    }
}