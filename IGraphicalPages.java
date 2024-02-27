// --== CS400 Project Three File Header ==--
// Name: Luke Wolfram
// CSL Username: lwolfram
// Email: lwolfram@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader:
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface IGraphicalPages {

    /**
     * This abstract method displays the main menu.
     */
    public Scene mainMenu();

    /**
     * This abstract method displays the map.
     * @return 
     */
    public Pane map();

}

