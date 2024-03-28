import java.util.ArrayList;
import java.util.Collections;

public class HamiltonCircuit {
    private UnweightedGraph G;
    private int[] pre;
    private int end;

    //O(n!)
    //using idea of backtracking
    public HamiltonCircuit(UnweightedGraph G){
        this.G = G;
        //use idea of state compression
        //each bit represents the state of vertices v starting from right
        int visited = 0;
        pre = new int[G.V()];
        end = -1;

        dfs(visited , 0, 0, G.V());
    }

    //variable left represents the number of vertices that have not yet been visited
    private boolean dfs(int visited, int v, int parent, int left){
        visited += (1 << v);
        pre[v] = parent;
        left--;

        if (left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for (int w: G.adj(v)){
            if ((visited & (1 << w)) == 0) {
                if (dfs(visited, w, v, left))
                    return true;
            }
        }

        return false;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();

        if (end == -1)
            return res;

        int cur = end;
        res.add(0);

        while (cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);

        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g3.txt");
        HamiltonCircuit hamiltonCircuit = new HamiltonCircuit(G);

        System.out.println(hamiltonCircuit.result());
    }
}