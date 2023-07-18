import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleGraph implements Graph {
    private List<UndirectedEdge> edges;
    private List<Node> nodes_isolated;

    public SimpleGraph(){
        edges = new ArrayList<UndirectedEdge>();
    }
    public void addEdge(Node A, Node B){
        UndirectedEdge e = new UndirectedEdge(A, B);
        if (edges.contains(e)) return;
        else edges.add(e);
    }
    public void addEdge(Node A, Node B, Object attribute){
        UndirectedEdge e = new UndirectedEdge(attribute, A, B);
        if (edges.contains(e)) return;
        else edges.add(e);
    }
    public void addNode(Node node){
        if (nodes_isolated.contains(node)) return;
        for (UndirectedEdge e : edges)
              if (e.getNodes().first.equals(node) || e.getNodes().second.equals(node)) return;
        nodes_isolated.add(node);
    }
    public void removeEdge(Node A, Node B){
        UndirectedEdge e = new UndirectedEdge(A, B);
        Pair<Node, Node> nodes = e.getNodes();
        if (edges.contains(e)) edges.remove(e);
        this.addNode (nodes.first);
        this.addNode (nodes.second);
    }
    public void removeNode(Node node){
        if (nodes_isolated.contains(node)) nodes_isolated.remove(node);
        List<UndirectedEdge> toRemove = new ArrayList<UndirectedEdge>();
        for (UndirectedEdge e : edges)
              if (e.getNodes().first.equals(node) || e.getNodes().second.equals(node)) toRemove.add(e);
        for (UndirectedEdge e : toRemove) edges.remove(e);
    }
    public boolean hasEdge(Node A, Node B){
        UndirectedEdge e = new UndirectedEdge(A, B);
        return edges.contains(e);
    }
    public boolean hasNode(Node node){
        for (UndirectedEdge e : edges)
              if (e.getNodes().first.equals(node) || e.getNodes().second.equals(node)) return true;
        return nodes_isolated.contains(node);
    }
    public int getEdgeCount(){
        return edges.size();
    }
    public int getNodeCount(){
        int res = 0;
        for (UndirectedEdge e : edges){
            Pair<Node, Node> nodes = e.getNodes();
            if (!nodes_isolated.contains(nodes.first)) res++;
            if (!nodes_isolated.contains(nodes.second)) res++;
        }
        return res + nodes_isolated.size();
    }
    public List<Edge> getEdges(){
        return new ArrayList<Edge>(edges);
    }
    public List<Node> getNodes(){
         List<Node> result = new ArrayList<Node>(nodes_isolated);
         for (UndirectedEdge e : edges){
             Pair<Node, Node> nodes = e.getNodes();
             if (!result.contains(nodes.first)) result.add(nodes.first);
             if (!result.contains(nodes.second)) result.add(nodes.second);
         }
         return result;
    }

    public boolean contains(Graph g){
        if (g == null) return false;
        if (!(g instanceof SimpleGraph)) return false;
        SimpleGraph sg = (SimpleGraph) g;
        for (Object e : sg.getEdges())
              if (!edges.contains(e)) return false;
        return true;
    }
    public Graph getShortestPath(Node n1, Node n2){
        return null;
    }
    public Graph getWeightedShortestPath(Node A, Node B) throws GraphNotWeightedException{
        return null;
    }
    public boolean isAcyclic(){
        return false;
    }
public Graph getUnion(Graph g){
        return null;
    }
    public Graph getIntersection(Graph g){
        return null;
    }
    public Iterator<Node> dfsIterator(Node start){
        return null;
    }
    public Iterator<Node> bfsIterator(Node start){
        return null;
    }
    public Iterator<Node> iterator(){
        return null;
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (!(o instanceof SimpleGraph)) return false;
        SimpleGraph g = (SimpleGraph) o;
        return edges.equals(g.edges);
    }
    @Override
    public int hashCode(){
        return edges.hashCode();
    }
    @Override
    public String toString(){
        return edges.toString();
    }



}
