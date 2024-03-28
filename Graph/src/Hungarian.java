import java.util.*;

public class Hungarian {

    private UnweightedGraph G;
    private int maxMatching = 0;
    private int[] matching;

    //dfs
    private boolean[] visited;

    //O(V*E)
    public Hungarian(UnweightedGraph G){
        BipartitionDetection bd = new BipartitionDetection(G);
        if (!bd.isBipartite())
            throw new IllegalArgumentException("Not a bipartite graph");

        this.G = G;

        int[] colors = bd.colors();

        matching = new int[G.V()];
        Arrays.fill(matching, -1);

        //dfs
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++){
            if (colors[v] == 0 && matching[v] == -1){
                //dfs
                Arrays.fill(visited, false);
                if(dfs(v)) maxMatching++;

                //if(bfs(v)) maxMatching++;

            }
        }
    }

    public boolean dfs(int v){
        visited[v] = true;

        for (int w: G.adj(v)){
            if (!visited[w]){
                visited[w] = true;

                if (matching[w] == -1 || dfs(matching[w])){
                    matching[v] = w;
                    matching[w] = v;

                    return true;
                }
            }
        }

        return false;
    }

    private boolean bfs(int v){
        Queue<Integer> queue = new LinkedList<>();
        int[] pre = new int[G.V()];
        Arrays.fill(pre, -1);

        queue.add(v);
        pre[v] = v;

        while (!queue.isEmpty()){
            int cur = queue.poll();

            for (int next: G.adj(cur)) {
                if (pre[next] == -1){
                    if (matching[next] != -1){
                        queue.add(matching[next]);
                        pre[next] = cur;
                        pre[matching[next]] = next;
                    }
                    else{
                        pre[next] = cur;
                        ArrayList<Integer> augPath = getAugPath(pre, v, next);
                        for (int i = 0; i < augPath.size(); i += 2){
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre, int start, int end){
        ArrayList<Integer> res = new ArrayList<>();
        int cur = end;

        while (cur != start){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);

        return res;
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/m2.txt");
        Hungarian hungarian = new Hungarian(G);
        System.out.println(hungarian.maxMatching());
        System.out.println(hungarian.isPerfectMatching());
    }
}