public interface Graph {
    int V();
    int E();
    boolean hasEdge(int v, int w);
    Iterable<Integer> adj(int v);
    int degree(int v);
    void validateVertex(int v);
    void removeEdge(int v, int w);
    boolean isDirected();
}