public class BipartiteMatching {

    private UnweightedGraph G;
    private int maxMatching;

    public BipartiteMatching(UnweightedGraph G){
        BipartitionDetection bd = new BipartitionDetection(G);
        if (!bd.isBipartite())
            throw new IllegalArgumentException("Not a bipartite graph");

        this.G = G;

        int[] colors = bd.colors();

        //source is V and target is V + 1
        WeightedGraph network = new WeightedGraph(G.V() + 2, true);
        for (int v = 0; v < G.V(); v++){
            if (colors[v] == 0)
                network.addEdge(G.V(), v, 1);
            else
                network.addEdge(v, G.V() + 1, 1);

            for (int w: G.adj(v)) {
                if (v < w) {
                    if (colors[v] == 0)
                        network.addEdge(v, w, 1);
                    else
                        network.addEdge(w, v, 1);
                }
            }
        }

        MaxFlow maxFlow = new MaxFlow(network, G.V(), G.V() + 1);
        maxMatching = maxFlow.result();
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/m2.txt");
        BipartiteMatching bipartiteMatching = new BipartiteMatching(G);
        System.out.println(bipartiteMatching.maxMatching());
        System.out.println(bipartiteMatching.isPerfectMatching());
    }
}