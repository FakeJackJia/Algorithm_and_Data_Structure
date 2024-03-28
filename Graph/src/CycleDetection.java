public class CycleDetection {
    private UnweightedGraph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    //check whether graph has cycle
    public CycleDetection(UnweightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++){
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int parent){
        visited[v] = true;

        for (int w: G.adj(v)){
            if (!visited[w]) {
                if (dfs(w, v))
                    return true;
            }
            else if (w != parent)
                return true;
        }

        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        CycleDetection cycleDetection = new CycleDetection(G);
        System.out.println(cycleDetection.hasCycle());
    }
}