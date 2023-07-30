public class UndirectedEdge <T> extends Edge<T>{
    public UndirectedEdge(Node A, Node B){super(A, B);}
    public UndirectedEdge(T attribute, Node A, Node B){super(attribute, A, B);}

}
