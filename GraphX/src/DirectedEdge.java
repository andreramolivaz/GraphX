public class DirectedEdge<T> extends Edge <T> {
    public DirectedEdge(Node source, Node target){
        super(source, target);
    }
    public DirectedEdge(T attribute, Node source, Node target){
        super(attribute, source, target);
    }
    public Node getSource(){return super.nodes.first;}
    public Node getTarget(){return super.nodes.second;}
    public void setSource(Node source){super.nodes.first = source;}
    public void setTarget(Node target){super.nodes.second = target;}
    public void getDirection(){
        System.out.println("Edge direction: " + this.getSource() + " -> " + this.getTarget());
    }
    public void setDirection(Node source, Node target){
        this.setSource(source);
        this.setTarget(target);
    }
    public void reverseDirection(){
        Node temp = this.getSource();
        this.setSource(this.getTarget());
        this.setTarget(temp);
    }
}