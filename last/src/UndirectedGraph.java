public class UndirectedGraph extends Graph {
    Edge[] edges;
    int numberOfVertices;

    public UndirectedGraph( int numberOfVertices,Edge[] edges) {
        this.edges = edges;
        this.numberOfVertices = numberOfVertices;
    }
}
