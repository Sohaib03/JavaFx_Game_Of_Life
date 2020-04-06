package GameOfLife;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import GameOfLife.GameLoop;
import GameOfLife.GameOfLife;


public class Main {

    private final int WIDTH = 100, HEIGHT = 100;
    Stage primaryStage;

    GameLoop gameLoop;
    public Main() {
        primaryStage = new Stage();
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
        gameLoop = new GameLoop(gameOfLife);
        gameLoop.start();

        anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int)(event.getSceneX()/11);
                int y = (int) (event.getSceneY()/11);
                System.out.println(gameLoop.running);
                if (!gameLoop.running && x<gameOfLife.getWidth() && y < gameOfLife.getHeight()) {
                    System.out.printf("%d %d\n",x, y);
                    gameOfLife.toggle(x, y);
                    gameOfLife.updateGridPane();
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }
    public GameLoop getGameLoop() { return gameLoop;}

    public Stage getGameStage() {
        return primaryStage;
    }
}
