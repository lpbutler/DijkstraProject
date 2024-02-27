// --== CS400 Project One File Header ==--
// Name: Mohammed Habeeb
// CSL Username: habeeb
// Email: mahabeeb@wisc.edu
// Lecture #:  003 @2:25pm
// Notes to Grader: <any optional extra notes to your grader>


import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author CS400
 * @author Mohammed Habeeb & Nika Logofet
 */

/**
 * This interface represents a directed graph data structure with positive edge weights.
 *
 * @param NodeType the data type stored at each graph vertex
 * @param EdgeType the data type stored at each graph edge as a Number whose doubleValue() method returns a value >=0.0
 */
public interface GraphADT<NodeType,EdgeType extends Number> {

    /**
     * Insert a new vertex into the graph.
     *
     * @param data the data item stored in the new vertex
     * @return true if the data can be inserted as a new vertex, false if it is already in the graph
     * @throws NullPointerException if data is null
     */
    public boolean insertVertex(NodeType data);


    /**
     * Insert a new directed edge with a positive edges weight into the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @param weight the weight for the edge (has to be a positive Number)
     * @return true if the edge could be inserted or its weight updated, false if the edge with the same weight was already in the graph with the graph
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph, or weight is < 0
     * @throws NullPointerException     if either sourceVertex or targetVertex or both are null
     */
    public boolean insertEdge(NodeType source, NodeType target, EdgeType weight);

    /**
     * Check if the graph contains a vertex with data item *data*.
     *
     * @param data the data item to check check for
     * @return true if data item is stored in a vertex of the graph, false otherwise
     * @throws NullPointerException if *data* is null
     */
    public boolean containsVertex(NodeType data);

    /**
     * Check if edge is in the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge is in the graph, false if it is not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean containsEdge(NodeType source, NodeType target);

}

