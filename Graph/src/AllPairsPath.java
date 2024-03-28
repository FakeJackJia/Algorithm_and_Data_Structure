public class AllPairsPath {
    private UnweightedGraph G;
    private SingleSourcePath[] paths;

    public AllPairsPath(UnweightedGraph G){
        this.G = G;

        paths = new SingleSourcePath[G.V()];

        for (int v = 0; v < G.V(); v++)
            paths[v] = new SingleSourcePath(G, v);
    }

    public boolean isConnectedTo(int s, int t){
        G.validateVertex(s);

        return paths[s].isConnectedTo(t);
    }

    public Iterable<Integer> path(int s, int t){
        G.validateVertex(s);

        return paths[s].path(t);
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g.txt");
        AllPairsPath allPairsPath = new AllPairsPath(G);
        System.out.println(allPairsPath.path(3, 6));
    }
}
