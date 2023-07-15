public abstract class Edge <T> {
    private T attribute;
    private Pair<Node, Node> nodes;

    public Edge(Node source, Node target){
        this.nodes = new Pair<>(source, target);
    }

    public Edge(T attribute, Node source, Node target){
        this(source, target);
        this.attribute = attribute;
    }

    public T getAttribute() throws EdgeWithoutAttributeException{
        if (this.attribute != null) return this.attribute;
        else throw new EdgeWithoutAttributeException();
    }
    public void setAttribute(T attribute){
        this.attribute = attribute;
    }
    public Node getSource(){
        return nodes.first;
    }
    public Node getTarget(){
        return nodes.second;
    }
    public void setSource(Node source){
        this.nodes.first = source;
    }
    public void setTarget(Node target){
        this.nodes.second = target;
    }
    public boolean isLabeled(){
        if(attribute instanceof String) return true;
        else return false;
    }
    public boolean isWeighted(){
        if(attribute instanceof Number) return true;
        else return false;
    }
}
