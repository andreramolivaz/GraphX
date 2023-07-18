import java.util.ArrayList;
import java.util.List;

public class SimpleGraph<T> extends Graph{
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
