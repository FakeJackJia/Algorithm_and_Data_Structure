import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class EulerCircuitDirected {

    private UnweightedGraph G;

    //A directed unweighted connected graph has an Euler circuit iff all vertices' indegree = outdegree
    public EulerCircuitDirected(UnweightedGraph G){
        this.G = G;
    }

    public boolean hasEulerCircuit(){
//        ConnectedComponent cc = new ConnectedComponent(G);
//
//        if (cc.count() > 1)
//            return false;

        for (int v = 0; v < G.V(); v++){
            if (G.indegree(v) != G.outdegree(v))
                return false;
        }

        return true;
    }

    //O(V + E)
    //Hierholzer's algorithm
    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerCircuit())
            return res;

        UnweightedGraph g = (UnweightedGraph) G.clone();
        Stack<Integer> stack = new Stack<>();
        int curV = 0;
        stack.push(curV);

        while (!stack.isEmpty()){
            if (g.outdegree(curV) != 0){
                stack.push(curV);

                int w = g.adj(curV).iterator().next();

                g.removeEdge(curV, w);
                curV = w;
            }
            else{
                res.add(curV);
                curV = stack.pop();
            }
        }

        //as the graph is directed
        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/gd1.txt", true);
        EulerCircuitDirected eulerCircuitDirected = new EulerCircuitDirected(G);
        System.out.println(eulerCircuitDirected.result());
    }
}