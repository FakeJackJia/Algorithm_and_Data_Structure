import java.util.Arrays;

public class BipartitionDetection {
    private UnweightedGraph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(UnweightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];

        Arrays.fill(colors, - 1);

        for (int v = 0; v < G.V(); v++){
            if (!visited[v]){
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color){
        visited[v] = true;
        colors[v] = color;

        for (int w: G.adj(v)){
            if (!visited[w]) {
                if (!dfs(w, 1 - color))
                    return false;
            }
            else if (colors[w] == colors[v]){
                return false;
            }
        }

        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public int[] colors(){
        return colors;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(G);

        System.out.println(bipartitionDetection.isBipartite());
    }
}