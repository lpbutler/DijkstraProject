// --== CS400 Project Three File Header ==--
// Name: Luke Wolfram
// CSL Username: lwolfram
// Email: lwolfram@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader:

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GraphicalPages extends Application implements IGraphicalPages {

    private int score = 0;
    Stage window;
    Scene mainMenu, easyModeScene, medModeScene, hardModeScene;
    Button goToEasy, goToMed, goToHard;
    String userShortestPath = "[", startDest = "", endDest = "", randomEnd = "",
        randomStart = "Memorial_Union";
    ArrayList<String> places = new ArrayList<String>();
    BackendInsert backend = new BackendInsert();
    TripPlannerDijkstra<String, Double> graph;
    /**
     * This method overrides the abstract method of "Application" class.
     *
     * @param primaryStage main stage that will display all scenes
     */
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        backend.insertIntoWeightedGraph();
        graph =  backend.g;
        mainMenu = mainMenu();

        window.setScene(mainMenu);
        window.setTitle("Campus Tour");
        places = backend.gl.getPlaces();

        window.show();
    }

    /**
     * This method overrides the abstract method of "IGraphicalPages" class, which displays the map.
     *
     * @return a pane of the interactable map
     */
    @Override
    public Pane map() {
        Pane toReturn = new Pane();

        Image image = new Image("graph.dot.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600); imageView.setFitWidth(800);

        Button memu = new Button("Memorial_Union"); memu.setLayoutX(280); memu.setLayoutY(3);
        Button usouth = new Button("Union_South"); usouth.setLayoutX(375); usouth.setLayoutY(66);
        Button pp = new Button("Picnic_Point"); pp.setLayoutX(293); pp.setLayoutY(130);
        Button sel = new Button("Sellery"); sel.setLayoutX(305); sel.setLayoutY(192);
        Button cap = new Button("Wisconsin_Capital"); cap.setLayoutX(470); cap.setLayoutY(255);
        Button bhill = new Button("Bascom_Hill"); bhill.setLayoutX(480); bhill.setLayoutY(318);
        Button cr = new Button("Camp_Randall"); cr.setLayoutX(510); cr.setLayoutY(382);
        Button kc = new Button("Khol_Center"); kc.setLayoutX(230); kc.setLayoutY(445);
        Button h = new Button("University_Hospital"); h.setLayoutX(300); h.setLayoutY(508);
        Button cl = new Button("College_Library"); cl.setLayoutX(308); cl.setLayoutY(570);

        ArrayList<Button> buttonEventHandler = new ArrayList<>
            (Arrays.asList(memu, usouth, pp, sel, cap, bhill, cr, kc, h, cl));

        for (Button b : buttonEventHandler) {
            b.setOnAction(e -> userShortestPath += (b.getText() + ", "));
        }

        Button finishGame = new Button("Finish Sequence");
        finishGame.setLayoutX(703);
        finishGame.setOnAction(e -> printUserResults());

        Button giveAnswers = new Button("Give Answers");
        giveAnswers.setLayoutX(715); giveAnswers.setLayoutY(24);
        giveAnswers.setOnAction(e -> printActualPath());

        Button randomizeDests = new Button("Randomize end");
        randomizeDests.setLayoutX(605);
        randomizeDests.setOnAction(e -> randomizeEnd());

        Button goToMenu = new Button("Return to menu");
        goToMenu.setOnAction(e -> {window.setScene(mainMenu); userShortestPath = "[";});

        Text pathToFind = new Text("Find shortest path from: " + randomStart + " to " + randomEnd);
        pathToFind.setLayoutX(425); pathToFind.setLayoutY(595);
        pathToFind.setFill(Color.RED);

        toReturn.getChildren().addAll(imageView, goToMenu, finishGame, giveAnswers, pathToFind,
            randomizeDests, memu, usouth, pp, sel, cap, bhill, cr, kc, h, cl);

        goToMenu.setId("Menu");
        finishGame.setId("finish");

        return toReturn;
    }

    /**
     * Builds the main menu scene.  Has names, title, difficulty options, and an exit
     *
     * @return a scene where users can navigate to different difficulties from
     */
    public Scene mainMenu() {
        BorderPane bp = new BorderPane();

        Text titleText = new Text();
        titleText.setText("Campus Tour");
        titleText.setX(230); titleText.setY(50);
        titleText.setFont(Font.font("Verdana", 50)); titleText.setFill(Color.BLACK);

        bp.getChildren().add(titleText);

        Text authorNames = new Text();
        authorNames.setText("By: Lucas S., Lucia B., Mohammed H., Luke W.");
        authorNames.setX(220); authorNames.setY(95);
        authorNames.setFont(Font.font("Verdana", 15)); authorNames.setFill(Color.BLACK);

        bp.getChildren().add(authorNames);

        Text difficultySelection = new Text();
        difficultySelection.setText("Select a difficulty");
        difficultySelection.setX(313); difficultySelection.setY(575);
        difficultySelection.setFont(Font.font("Verdana", 20));
        difficultySelection.setFill(Color.BLACK);

        bp.getChildren().add(difficultySelection);

        //Difficulty Selection Boxes
        Rectangle easyBoxExt = new Rectangle();
        easyBoxExt.setX(300); easyBoxExt.setY(126);
        easyBoxExt.setWidth(195); easyBoxExt.setHeight(120);
        easyBoxExt.setFill(Color.BLACK);

        bp.getChildren().add(easyBoxExt);

        Rectangle easyBoxInt = new Rectangle();
        easyBoxInt.setX(310); easyBoxInt.setY(136);
        easyBoxInt.setWidth(175); easyBoxInt.setHeight(100);
        easyBoxInt.setFill(Color.SEAGREEN);
        bp.getChildren().add(easyBoxInt);

        Rectangle medBoxExt = new Rectangle();
        medBoxExt.setX(300); medBoxExt.setY(267); medBoxExt.setWidth(195); medBoxExt.setHeight(120);
        medBoxExt.setFill(Color.BLACK);

        bp.getChildren().add(medBoxExt);

        Rectangle medBoxInt = new Rectangle();
        medBoxInt.setX(310); medBoxInt.setY(277); medBoxInt.setWidth(175); medBoxInt.setHeight(100);
        medBoxInt.setFill(Color.CORAL);

        bp.getChildren().add(medBoxInt);

        Rectangle hardBoxExt = new Rectangle();
        hardBoxExt.setX(300);hardBoxExt.setY(410); hardBoxExt.setWidth(195);hardBoxExt.setHeight(120);
        hardBoxExt.setFill(Color.BLACK);

        bp.getChildren().add(hardBoxExt);

        Rectangle hardBoxInt = new Rectangle();
        hardBoxInt.setX(310);hardBoxInt.setY(420); hardBoxInt.setWidth(175);hardBoxInt.setHeight(100);
        hardBoxInt.setFill(Color.CRIMSON);

        bp.getChildren().add(hardBoxInt);

        Pane difficultyButtons = new Pane();
        bp.setCenter(difficultyButtons);
        BorderPane.setAlignment(difficultyButtons, Pos.CENTER);

        goToEasy = new Button("Easy Mode");
        goToEasy.setFont(Font.font("Verdana", 20));
        goToEasy.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
            new CornerRadii(0), Insets.EMPTY)));
        goToEasy.setLayoutX(310); goToEasy.setLayoutY(136);
        goToEasy.setMinWidth(175); goToEasy.setMinHeight(100);
        goToEasy.setOnAction(e -> {easyModeScene = buildGameScene(); window.setScene(easyModeScene);
            randomizeEnd();});


        goToMed = new Button("Medium Mode");
        goToMed.setFont(Font.font("Verdana", 20));
        goToMed.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
            new CornerRadii(0), Insets.EMPTY)));
        goToMed.setLayoutX(310); goToMed.setLayoutY(277);
        goToMed.setMinWidth(175); goToMed.setMinHeight(100);
        goToMed.setOnAction(e -> {medModeScene = buildGameScene(); window.setScene(medModeScene);
            randomizeEnd();});

        goToHard = new Button("Hard Mode");
        goToHard.setFont(Font.font("Verdana", 20));
        goToHard.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
            new CornerRadii(0), Insets.EMPTY)));
        goToHard.setLayoutX(310); goToHard.setLayoutY(420);
        goToHard.setMinWidth(175); goToHard.setMinHeight(100);
        goToHard.setOnAction(e -> {hardModeScene = buildGameScene(); window.setScene(hardModeScene);
            randomizeEnd();});

        goToEasy.setId("goToEasy"); goToMed.setId("goToMed"); goToHard.setId("goToHard");
        difficultyButtons.getChildren().addAll(goToEasy, goToMed, goToHard);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {Platform.exit();});
        exitButton.setMinWidth(100); exitButton.setMinHeight(50);
        exitButton.setFont(Font.font("Verdana", 20));
        bp.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

        bp.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
            new CornerRadii(0), Insets.EMPTY)));
        Scene toReturn = new Scene(bp, 800, 600, Color.LIGHTSKYBLUE);
        return toReturn;
    }

    /**
     * Builds the scene of whatever difficulty option
     *
     * @return a scene to be displayed
     */
    public Scene buildGameScene() {
        Pane p = map();
        Scene toReturn = new Scene(map(), 800, 600);
        return toReturn;
    }

    /**
     * Prints whether or not the user got the path correct, as well as the user's path's cost.
     * Updates and prints score as well
     */
    public void printUserResults() {
        if (userShortestPath.length() == 1) {
            System.out.println("No nodes selected");
        } else {


            userShortestPath = userShortestPath.substring(0, userShortestPath.length() - 2) + "]";

            String pathToArray = userShortestPath.substring(1, userShortestPath.length() - 1);

            String[] userArray = pathToArray.split(", ");
            double sum = 0.0;

            //Gets the cost of the user's path

            for (int i = 0; i < userArray.length - 1; i++) {
                if (userArray[i].equals(graph.vertices.get(userArray[i]).data)) {
                    for (int j = 0; j < graph.vertices.get(userArray[i]).edgesLeaving.size(); j++) {
                        if (userArray[i + 1].equals(graph.vertices.get(userArray[i]).edgesLeaving.get(j).target.data)) {
                            sum += graph.vertices.get(userArray[i]).edgesLeaving.get(j).weight;
                        }
                    }
                }
            }





            String userStart = userShortestPath.substring(userShortestPath.indexOf("[") + 1,
                userShortestPath.indexOf(","));
            String userEnd = userShortestPath.substring(userShortestPath.lastIndexOf(",") + 2,
                userShortestPath.indexOf("]"));

            System.out.println("Your Path:     " + userShortestPath
                + " with a cost of " + sum);

            String check = userShortestPath.substring(userShortestPath.indexOf("["),
                userShortestPath.length());


            if (check.equals(graph.shortestPath(randomStart, randomEnd).toString())) {
                System.out.println("Correct sequence!");
                randomizeEnd();
                score++;
            } else {
                System.out.println("Incorrect sequence, keep trying!");
                if (score > 0) {
                    score--;
                }
            }
        }
        System.out.println("Score: " + score);
        userShortestPath = "[";
    }

    /**
     * Prints the shortest path and cost from randomStart to randomEnd, computed from AE's Graph
     */
    public void printActualPath() {
        System.out.println("Shortest Path: " + graph.shortestPath(randomStart, randomEnd) +
            " with a cost of " + graph.getPathCost(randomStart, randomEnd));
    }

    /**
     * Randomizes the ending node, as it is always set to start at memorial union
     * Possible end nodes include:
     * Easy Mode: Union South, Picnic Point, Sellery
     * Medium Mode: Wisconsin Capitol, Bascom Hill, Camp Randall
     * Hard Mode: Kohl Center, University Hospital, College Library
     */
    public void randomizeEnd() {
        Random randy = new Random();
        String temp = randomEnd;

        if (window.getScene().equals(easyModeScene)) {
            while(temp.equals(randomEnd)) {
                randomEnd = places.get(randy.nextInt(3) + 1);
            }
            easyModeScene = buildGameScene();
            window.setScene(easyModeScene);
            return;
        }

        if (window.getScene().equals(medModeScene)) {
            while(temp.equals(randomEnd)) {
                randomEnd = places.get(randy.nextInt(3) + 4);
            }
            medModeScene = buildGameScene();
            window.setScene(medModeScene);
            return;
        }

        while(temp.equals(randomEnd)) {
            randomEnd = places.get(randy.nextInt(3) + 7);
        }
        hardModeScene = buildGameScene();
        window.setScene(hardModeScene);
        return;
    }

    /**
     * Runs the Campus Tour application
     * @param args if any
     */
    public static void main(String[] args) {
        Application.launch();
    }
}

