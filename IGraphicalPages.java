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

