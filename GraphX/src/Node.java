import java.util.HashMap;
import java.util.Map;

public class Node{

    private Map <String, Object> attributes;

    public Node(){
        this.attributes = new HashMap<>();
    }

    public Node (Map<String, Object> attributes){
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Object getAttribute(String key){
        return this.attributes.get(key);
    }

    public void setAttribute(String key, Object value){
        this.attributes.put(key, value);
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public boolean hasAttribute(String name) {
        return attributes.containsKey(name);
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public boolean hasAttributeOfType(String name, Class<?> type) {
        Object temp = attributes.get(name);
        return temp != null && type.isInstance(temp);
    }

    @Override
    public String toString() {
        return "Node [attributes=" + attributes + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Node)) return false;
        Node temp = (Node) obj;
        return attributes.equals(temp.attributes);
    }

    @Override
    public int hashCode() {
        return attributes.hashCode();
    }

    @Override
    public Node clone() {
        Node temp = new Node(attributes);
        return temp;
    }
}
