import java.util.Iterator;
import java.util.List;

interface Graph extends Iterable<Node>{
    public void addEdge(Node A, Node B);
    public void addEdge(Node A, Node B, Object attribute);
    public void addNode(Node node);
    public void removeEdge(Node A, Node B);
    public void removeNode(Node node);
    public boolean hasEdge(Node A, Node B);
    public boolean hasNode(Node node);
    public int getEdgeCount();
    public int getNodeCount();
    public List<Edge> getEdges();
    public List<Node> getNodes();
    public boolean isSubgraph(Graph g);
    public Graph getShortestPath(Node n1, Node n2);
    public Graph getWeightedShortestPath(Node A, Node B) throws GraphNotWeightedException;
    public boolean isAcyclic();
    public Graph getUnion(Graph g);
    public Graph getIntersection(Graph g);
    public Iterator<Node> dfsIterator(Node start);
    public Iterator<Node> bfsIterator(Node start);
}
