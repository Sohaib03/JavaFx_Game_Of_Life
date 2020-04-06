package GameOfLife;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;


public class GameLoop {
    private AnimationTimer mainLoop;
    private long last_time =2000_000_000;
    private GameOfLife gameOfLife;
    public boolean running = false;
    int turns =0;
    public GameLoop(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
        mainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - last_time > 100_000_000) {
                    gameOfLife.updateGridPane();
                    gameOfLife.updateGrid();

                    last_time = now;
                    turns++;
                }
            }
        };
    }

    public void start() {
        mainLoop.start();
        running = true;
    }

    public AnimationTimer getMainLoop() {
        return mainLoop;
    }

    public void pause(){
        gameOfLife.updateGridPane();
        mainLoop.stop();
        running = false;
    }

    public void stop() {
        gameOfLife.reset();
        mainLoop.stop();
    }
}

