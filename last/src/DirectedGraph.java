public class DirectedGraph extends Graph {
    Edge[] edges;
    int numberOfVertices;

    public DirectedGraph( int numberOfVertices,Edge[] edges) {
        this.edges = edges;
        this.numberOfVertices = numberOfVertices;
    }
}
