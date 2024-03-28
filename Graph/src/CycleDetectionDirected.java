public class CycleDetectionDirected {
    private UnweightedGraph G;
    private boolean[] visited;
    private boolean hasCycle = false;
    private boolean[] onPath;

    //check whether graph has cycle
    public CycleDetectionDirected(UnweightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];
        onPath = new boolean[G.V()];

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
        onPath[v] = true;

        for (int w: G.adj(v)){
            if (!visited[w]) {
                if (dfs(w, v))
                    return true;
            }
            //can have a cycle back to its parent
            else if (onPath[w])
                return true;
        }

        onPath[v] = false;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/gd.txt", true);
        CycleDetectionDirected cycleDetectionDirected = new CycleDetectionDirected(G);
        System.out.println(cycleDetectionDirected.hasCycle());
    }
}