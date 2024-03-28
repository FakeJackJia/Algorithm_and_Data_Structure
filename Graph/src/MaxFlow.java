import java.util.*;

public class MaxFlow {

    private WeightedGraph network;
    private int s, t;

    //residual graph
    private WeightedGraph rG;
    private int maxFlow = 0;

    //Edmonds Karp Algorithm
    //O(V*E*E)
    public MaxFlow(WeightedGraph network, int s, int t){
        if (!network.isDirected())
            throw new IllegalArgumentException("Max flow only works in directed");

        if (network.V() < 2)
            throw new IllegalArgumentException("Network should have at least two vertices");

        network.validateVertex(s);
        network.validateVertex(t);

        if (s == t)
            throw new IllegalArgumentException("s and t should be different");

        this.network = network;
        this.s = s;
        this.t = t;

        this.rG = new WeightedGraph(network.V(), true);
        for (int v = 0; v < network.V(); v++){
            for (int w: network.adj(v)){
                //capacity
                int c = network.getWeight(v, w);
                rG.addEdge(v, w, c);
                rG.addEdge(w, v, 0);
            }
        }

        while (true){
            ArrayList<Integer> augPath = getAugmentPath();
            if (augPath.size() == 0)
                break;

            int flow = Integer.MAX_VALUE;
            for (int i = 1; i < augPath.size(); i++){
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                flow = Math.min(flow, rG.getWeight(v, w));
            }

            maxFlow += flow;

            for (int i = 1; i < augPath.size(); i++){
                int v = augPath.get(i - 1);
                int w = augPath.get(i);


                rG.setWeight(v, w, rG.getWeight(v, w) - flow);
                rG.setWeight(w, v, rG.getWeight(w, v) + flow);
            }
        }
    }

    public ArrayList<Integer> getAugmentPath(){
        Queue<Integer> queue = new LinkedList<>();
        int[] pre = new int[network.V()];
        Arrays.fill(pre, -1);

        queue.add(s);
        pre[s] = s;

        while (!queue.isEmpty()){
            int cur = queue.poll();

            if (cur == t) break;

            for (int next: rG.adj(cur)){
                if (pre[next] == -1 && rG.getWeight(cur, next) > 0){
                    queue.add(next);
                    pre[next] = cur;
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        if (pre[t] == -1) return res;

        int cur = t;

        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);
        Collections.reverse(res);

        return res;
    }

    public int result(){
        return maxFlow;
    }

    public int flow(int v, int w){
        if (!network.hasEdge(v, w))
            throw new IllegalArgumentException("No edge exits");

        return rG.getWeight(w, v);
    }

    public static void main(String[] main){
        WeightedGraph network = new WeightedGraph("./GraphFile/network.txt", true);
        MaxFlow maxFlow = new MaxFlow(network, 0, 3);
        System.out.println(maxFlow.result());

        for (int v = 0; v < network.V(); v++){
            for (int w: network.adj(v)){
                System.out.println(String.format("%d-%d, %d / %d", v, w, maxFlow.flow(v, w), network.getWeight(v, w)));
            }
        }
    }
}