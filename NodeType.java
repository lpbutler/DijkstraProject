import java.util.ArrayList;
public class NodeType {
    protected String source;
    protected String target;
    private double weight;
    // private ArrayList<Node> leaving;

    public NodeType(String s, String t, double w){
        this.source = s;
        this.target = t;
        this.weight = w;
        //this.leaving = new ArrayList<>();
    }
    public NodeType(String s){
        this.source = s;
        this.target = s;
        this.weight = -1.0;
    }

}

