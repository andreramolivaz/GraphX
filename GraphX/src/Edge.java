public abstract class Edge <T> {
    protected T attribute;
    protected Pair<Node, Node> nodes;
    public Edge(Node A, Node B){this.nodes = new Pair<>(A, B);}
    public Edge(T attribute, Node A, Node B){
        this(A, B);
        this.attribute = attribute;
    }
    public T getAttribute() throws EdgeWithoutAttributeException{
        if (this.attribute != null) return this.attribute;
        else throw new EdgeWithoutAttributeException();
    }
    public void setAttribute(T attribute){
        this.attribute = attribute;
    }
    public boolean isLabeled(){
        if(attribute instanceof String) return true;
        else return false;
    }
    public boolean isWeighted(){
        if(attribute instanceof Number) return true;
        else return false;
    }
    public Pair<Node, Node> getNodes(){return this.nodes;}
    public void setNodes(Pair<Node, Node> nodes){this.nodes = nodes;}
    @Override
    public String toString() {
        return "Edge [attribute=" + attribute + ", nodes=" + nodes + "]";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Edge)) return false;
        Edge temp = (Edge) obj;
        return attribute.equals(temp.attribute) && nodes.equals(temp.nodes);
    }
    @Override
    public int hashCode() {
        return attribute.hashCode() + nodes.hashCode();
    }
}
