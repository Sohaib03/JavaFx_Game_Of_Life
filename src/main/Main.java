package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.UI;

import java.net.URL;


public class Main extends Application {

    private final int WIDTH = 100, HEIGHT = 100;

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 1100, 1000);

        Rectangle[][] rectangles = new Rectangle[WIDTH][HEIGHT];
        for (int i=0; i<WIDTH; i++)  {
            for (int j=0; j<HEIGHT; j++) {
                rectangles[i][j] = new Rectangle(10, 10, Color.WHITE);

                rectangles[i][j].setLayoutX(i*11);
                rectangles[i][j].setLayoutY(j*11);

                anchorPane.getChildren().add(rectangles[i][j]);
            }
        }

        GameOfLife gameOfLife = new GameOfLife(rectangles);
        GameLoop gameLoop = new GameLoop(gameOfLife);
        gameLoop.start();
        primaryStage.setScene(scene);
        primaryStage.show();

        // Settings for the game!!
        UI mainUI = new UI();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }
}
