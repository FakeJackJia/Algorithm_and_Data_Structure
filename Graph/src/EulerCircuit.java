import java.util.ArrayList;
import java.util.Stack;

public class EulerCircuit {

    private UnweightedGraph G;

    //An unweighted connected graph has an Euler circuit iff all vertices' degree is even
    public EulerCircuit(UnweightedGraph G){
        this.G = G;
    }

    public boolean hasEulerCircuit(){
        ConnectedComponent cc = new ConnectedComponent(G);

        if (cc.count() > 1)
            return false;

        for (int v = 0; v < G.V(); v++){
            if (G.degree(v) % 2 == 1)
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
            if (g.degree(curV) != 0){
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

        return res;
    }

    public static void main(String[] args){
        UnweightedGraph G = new UnweightedGraph("./GraphFile/g5.txt");
        EulerCircuit eulerCircuit = new EulerCircuit(G);
        System.out.println(eulerCircuit.result());
    }
}