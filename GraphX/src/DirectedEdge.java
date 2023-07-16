public class DirectedEdge<T> extends Edge <T> {
    public DirectedEdge(Node source, Node target){
        super(source, target);
    }
    public DirectedEdge(T attribute, Node source, Node target){
        super(attribute, source, target);
    }
    public Node getSource(){
        return super.getSource();
    }
    public Node getTarget(){
        return super.getTarget();
    }
    public void setSource(Node source){
        super.setSource(source);
    }
    public void setTarget(Node target){
        super.setTarget(target);
    }
    public boolean isLabeled(){
        return super.isLabeled();
    }
    public boolean isWeighted(){
        return super.isWeighted();
    }
}