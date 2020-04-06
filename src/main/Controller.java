package main;

import GameOfLife.Main;
import javafx.application.Platform;

public class Controller {
    Main game;
    public void exitButtonClicked() {
        System.out.println("Exit Button Clicked!");
        Platform.exit();
    }

    public void playButtonClicked() {
        if (game == null)
            game = new Main();
        else if (game.getGameStage().isShowing())
            game.getGameLoop().start();
        else
            game.getGameStage().show();
    }

    public void pauseButtonClicked() {
        if (game != null) {
            game.getGameLoop().pause();
        }
    }

    public void stopButtonClicked() {
        if (game != null) {
            game.getGameLoop().stop();
        }
    }
}
