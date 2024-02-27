// Name: Luke Wolfram
// CSL Username: lwolfram
// Email: lwolfram@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader:
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import edu.wisc.cs.cs400.JavaFXTester;
import java.util.Scanner;

/**
 * This class implements tests for GraphicalPages.java.
 */
public class FrontendDeveloperTests extends JavaFXTester {
    //test only memu -> all nodes  
    //test memu -> ... -> whatever

    public FrontendDeveloperTests() {
      super(GraphicalPages.class);
    }

    @Test
    public void test1() {
      Button toEasy = lookup("#goToEasy").query();

      clickOn(toEasy);

      Button mm = lookup("#Menu").query();

      assertEquals("Return to menu", mm.getText());
    }

    @Test
    public void test2() {
      Button toMed = lookup("#goToMed").query();

      clickOn(toMed);

      Button mm = lookup("#Menu").query();

      assertEquals("Return to menu", mm.getText());
    }

    @Test
    public void test3() {
      Button toHard = lookup("#goToHard").query();

      clickOn(toHard);

      Button mm = lookup("#Menu").query();

      assertEquals("Return to menu", mm.getText());
    }

    @Test
    public void test4() {
      Button toEasy = lookup("#goToEasy").query();

      clickOn(toEasy);

      Button mm = lookup("#Menu").query();

      clickOn(mm);

      Button toEasy2 = lookup("#goToEasy").query();

      assertEquals("Easy Mode", toEasy2.getText());
    }

    @Test
    public void test5() {
      Button toEasy = lookup("#goToEasy").query();

      clickOn(toEasy);

      Button mm = lookup("#Menu").query();

      clickOn(mm);

      Button toMed = lookup("#goToMed").query();

      clickOn(toMed);

      Button mm2 = lookup("#Menu").query();
      
      Button toHard = lookup("#goToHard").query();

      clickOn(toHard);

      assertEquals("Hard Mode", toHard.getText());
    }

    @Test
    public void IntegrationTest1() {
      GraphicalPages a = new GraphicalPages();
      assertEquals(a.graph.vertices.size(), 10);

    }

    @Test
    public void IntegrationTest2() {

    }

    @Test
    public void CodeReviewOfAlgorithmEngineer1() {

    }

    @Test
    public void CodeReviewOfAlgorithmEngineer2() {

    }
}


