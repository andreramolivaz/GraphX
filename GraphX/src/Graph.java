import java.util.Iterator;
import java.util.List;


interface Graph extends Iterable<Node>{
    public void addEdge(Node source, Node target);
    public void addEdge(Node source, Node target, Object attribute);
    public void addNode(Node node);
    public void removeEdge(Node source, Node target);
    public void removeNode(Node node);
    public boolean hasEdge(Node source, Node target);
    public boolean hasNode(Node node);
    public int getEdgeCount();
    public int getNodeCount();
    public List<Edge> getEdges();
    public List<Node> getNodes();
    public boolean isSubgraph(Graph g);
    public Graph getShortestPath(Node n1, Node n2);
    public Graph getWeightedShortestPath(Node n1, Node n2) throws GraphNotWeightedException;
    public boolean isAcyclic();
    public Graph getUnion(Graph g);
    public Graph getIntersection(Graph g);
    public Iterator<Node> dfsIterator(Node start);
    public Iterator<Node> bfsIterator(Node start);
}
