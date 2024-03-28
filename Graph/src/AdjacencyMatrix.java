import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyMatrix {
    private int V;
    private int E;
    private int[][] adj;

    //O(E), Space complexity: O(V^2)
    public AdjacencyMatrix(String filename){
        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();

            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative");

            adj = new int[V][V];
            E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("E must be non-negative");

            for (int i = 0; i < E; i++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if (a == b)
                    throw new IllegalArgumentException("Self loop detected");
                if (adj[a][b] == 1)
                    throw new IllegalArgumentException("Parrallel edge detected");

                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void validateVertex(int v){
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    //O(1)
    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    //O(V)
    public ArrayList<Integer> adj(int v){
        validateVertex(v);

        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1)
                res.add(i);
        }

        return res;
    }

    public int degree(int v){
        return adj(v).size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args){
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("./GraphFile/g.txt");
        System.out.println(adjacencyMatrix);
        System.out.println(adjacencyMatrix.adj(0));
    }
}