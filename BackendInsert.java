import java.util.Arrays;
import java.util.ArrayList;

public class BackendInsert{

    //protected GraphLoader gl = new GraphLoader(".\\src\\hard.dot");
    protected GraphLoader gl = new GraphLoader("/home/lwolfram/private/cs400/p3/hard.dot");
    public TripPlannerDijkstra g = new TripPlannerDijkstra();

    //@Override
    public void insertDestination(String dest) {
        //IF DESTINATION IS ALREADY A PLACE
        ArrayList<String> p = gl.getPlaces();
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).equals(dest)) {
                System.out.println("Place is already in graph.");
                return;
            }
        }
        p.add(dest);

        Double[][] destinationMatrix = gl.getAdjacencyMatrix();
        //add another row and column to destinations to create new info for the newDestination
        Double[][] dm = new Double[destinationMatrix.length + 1][destinationMatrix.length + 1];
        //sets values in destinationMatrix to dm
        for(int i = 0; i < destinationMatrix.length; i++){
            for(int j = 0; j < destinationMatrix.length; j++){
                dm[i][j] = destinationMatrix[i][j];
            }
        }
        //set last row to null (new destination has not given the weight/distance of other locations)
        //set last column of all the previous rows to null because it has yet to be set the distance to the new location
        for(int i = 0; i < dm.length; i++){
            dm[dm.length-1][i] = null;
            dm[i][dm.length-1] = null;
        }
        //set dm matrix to adjacency matrix
        gl.adjacencyMatrix = dm;
    }

   // @Override
    public void insertIntoWeightedGraph() {
        //Make graph object
        //Graph g = new Graph();
        //Make graphLoader object to get matrix and places
        Double[][] destinationMatrix = gl.getAdjacencyMatrix();
        ArrayList<String> p = gl.getPlaces();
        //Insert all vertices
        for (int i = 0; i < p.size(); i++) {
            g.insertVertex(p.get(i));
        }

        //Insert edges
        //this for-loop to keep track of the source
        for (int i = 0; i < destinationMatrix.length; i++) {
            //this for-loop to keep track of target and its weight
            for (int j = 0; j < destinationMatrix.length; j++) {
                if (destinationMatrix[i][j] != null) {
                    String source = p.get(i);
                    String target = p.get(j);
                    Double weight = destinationMatrix[i][j];
                    //inserting the edge
                    g.insertEdge(source, target, weight);
                }
            }
        }
    }
}

