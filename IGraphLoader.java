//Lucas Steplyk

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Defines a class that reads a DOT file and turns it into an Adjacency matrix
 */
public interface IGraphLoader {

    //DOT file name
    String file = "";

    // Holds String names of the places from the DOT file
    // Order of places in the array will correspond with the positions
    // in the adjacencyMatrix
    ArrayList<String> places = null;

    // 2D array that holds an adjacency matrix of the distance between 2 places
    Double[][] adjacencyMatrix = null;

    // Example of how places and adjacencyMatrix work together
    // -1 corresponds to no entry
    // Where: A = places[0], B = places[1], C = places[2], D = places[3]
    // and each int is the distance between the 2 places
    // Ex. distance from B to C (or C to B) is 2 (adjacencyMatrix[1][2] or adjacencyMatrix[2][1])
    //    A    B   C   D  --> places
    // A  -1   4   8   7
    // B  4   -1   2   3
    // C  8    2  -1   9
    // D  7    3   9  -1

    /**
     * Returns an arrayList that are the names of the places
     *
     * @return arrayList of Stings
     */
    public ArrayList<String> getPlaces();

    /**
     * Adjacency matrix representation of the Distances between 2 places
     *
     * @return 2D array of Doubles as distances
     */
    public Double[][] getAdjacencyMatrix();
}
