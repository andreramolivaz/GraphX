public abstract class Edge <T> {
    private T attribute;
    private Node source;
    private Node target;

    public Edge(Node source, Node target){
        this.source = source;
        this.target = target;
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
        return this.source;
    }
    public Node getTarget(){
        return this.target;
    }
    public void setSource(Node source){
        this.source = source;
    }
    public void setTarget(Node target){
        this.target = target;
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
