import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;

    private class Node implements Comparable<Node>{
        public int v, dis;

        public Node(int v, int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another){
            return this.dis - another.dis;
        }
    }

    //O(ElogE)
    public Dijkstra(WeightedGraph G, int s){
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        Arrays.fill(pre, -1);
        Arrays.fill(dis, Integer.MAX_VALUE);

        dis[s] = 0;
        pre[s] = s;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while(!pq.isEmpty()){
            int cur = pq.poll().v;
            if (visited[cur])
                continue;

            visited[cur] = true;
            for (int w: G.adj(cur)){
                if (!visited[w]){
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;
                    }
                }
            }
        }
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);

        return visited[v];
    }

    public int disTo(int v){
        G.validateVertex(v);

        return dis[v];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t))
            return res;

        int cur = t;

        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);

        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args){
        WeightedGraph G = new WeightedGraph("./GraphFile/gw1.txt");
        Dijkstra dijkstra = new Dijkstra(G, 0);

        for (int v = 0; v < G.V(); v++)
            System.out.print(dijkstra.disTo(v) + " ");

        System.out.println();
        System.out.println(dijkstra.path(3));
    }
}