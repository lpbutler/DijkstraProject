//Lucas Steplyk

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GraphLoader implements IGraphLoader {

    //DOT file name
    String file;

    // Holds String names of the places from the DOT file
    // Order of places in the array will correspond with the positions
    // in the adjacencyMatrix
    ArrayList<String> places;

    // 2D array that holds an adjacency matrix of the distance between 2 places
    Double[][] adjacencyMatrix;

    GraphLoader(String file) {
        this.file = file;
        this.places = new ArrayList<>();
        adjacencyMatrix = new Double[1][1];
        processFile();
    }


    /**
     * Returns an arrayList that are the names of the places
     *
     * @return arrayList of Stings
     */
    @Override 
    public ArrayList<String> getPlaces() {
        return this.places;
    }

    /**
     * Adjacency matrix representation of the Distances between 2 places
     *
     * @return 2D array of Doubles as distances
     */
    @Override 
    public Double[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }


    /**
     * Process the DOT file and turns it into an adjacency matrix
     */
    private void processFile() {

        try {
            //open file and use scanner to get data from
            File graph = new File(file);
            Scanner scanner = new Scanner(graph);
            //while file has more lines
            while (scanner.hasNextLine()) {
                //gets curr line
                String line = scanner.nextLine();
                //if at the end return
                if (line.contains("}")) {
                    return;
                }
                //if at the being continue
                if (line.contains("{")) {
                    continue;
                }

                //First split the line by the undirected symbol
                List<String> split = Arrays.asList(line.split("--"));
                //get the source location
                String place1 = split.get(0).trim();
                if (places == null || !places.contains(place1)) {
                    places.add(place1);
                }
                int place1Index = places.indexOf(place1);
                split = Arrays.asList(split.get(1).split("\\["));
                //gets the target location
                String place2 = split.get(0).trim();
                if (places == null || !places.contains(place2)) {
                    places.add(place2);
                }
                int place2Index = places.indexOf(place2);
                split = Arrays.asList(split.get(1).split("="));
                //gets distance and then places into matrix
                double dist =
                    Double.parseDouble(split.get(1).substring(1, split.get(1).length() - 3));
                extendMatrix();
                adjacencyMatrix[place1Index][place2Index] = dist;
                adjacencyMatrix[place2Index][place1Index] = dist;
            }
        } catch (FileNotFoundException e) {
            this.file = null;
            System.out.println("Error: File cannot be found");
        }
    }


    /**
     * extends the current adjacency matrix to make sure that it can fit all the places
     */
    private void extendMatrix() {
        Double[][] temp = adjacencyMatrix;
        adjacencyMatrix = new Double[places.size()][places.size()];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                adjacencyMatrix[i][j] = temp[i][j];
            }
        }
    }

    /**
     * turns places array into a string
     *
     * @return string rep of places
     */
    public String placesToString() {
        String string = "";
        for (String place : this.places) {
            string = string + place + ", ";
        }
        return string.trim();
    }

    /**
     * turns AM  2D array into a string
     *
     * @return string rep of AM
     */
    public String matrixToString() {
        String string = "";
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                string = string + adjacencyMatrix[i][j] + ",";
            }
            string = string + "\n";
        }
        return string.trim();
    }
}

