package main;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.io.File;

import java.util.Scanner;

public class GameOfLife {
    private int[][] grid;
    private int[][] next_grid;
    private int[][] changed;
    private static final int WIDTH = 100, HEIGHT = 100;
    private Rectangle[][] rectangles;
    public GameOfLife(Rectangle[][] rectangles) {
        this.rectangles = rectangles;
        grid = new int[WIDTH][HEIGHT];
        next_grid = new int[WIDTH][HEIGHT];
        changed = new int[WIDTH][HEIGHT];
        setGrid();
        updateGridPane();
    }



    public int[][] getGrid() {
        return grid;
    }
    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }

    public void setGrid() {
        loadFromFile();

    }

    public void loadFromFile() {
        try{
            Scanner input = new Scanner(new File("src/main/first_gen_state.txt"));
            int i=0;
            while (input.hasNextLine()){
                String line = input.nextLine();
                for (int j=0; j<line.length(); j++) {
                    grid[j][i] = line.charAt(j) - '0';
                    if (grid[j][i] == 1) setRectOn(rectangles[j][i]);
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("File Not found");
        }
    }
    public void updateGrid() {

        for (int i=0; i<WIDTH; i++) {
            for (int j=0; j<HEIGHT; j++) {

                int aliveN = aliveNeighbors(i, j);
                next_grid[i][j] = grid[i][j];
                changed[i][j] =0;
                if (grid[i][j] == 1) {
                    if (aliveN != 2 && aliveN != 3) {
                        changed[i][j] = 1;
                        next_grid[i][j] = 0;
                    }
                }
                else {
                    if (aliveN == 3) {
                        changed[i][j] =1;
                        next_grid[i][j] = 1;
                    }
                }
            }
        }
        for (int i=0; i<WIDTH; i++) {
            System.arraycopy(next_grid[i], 0, grid[i], 0, HEIGHT);
        }
    }

    public void updateGridPane () {
        for (int i=0; i<WIDTH; i++) {
            for (int j=0; j<HEIGHT; j++) {
                if (changed[i][j] == 0) continue;
                if (grid[i][j] == 1) setRectOn(rectangles[i][j]);
                else setRectOff(rectangles[i][j]);
            }
        }
    }



    public int aliveNeighbors(int i, int j) {
        int alive = 0;
        if (check(i-1, j) == 1) alive++;
        if (check(i-1, j-1) == 1) alive++;
        if (check(i-1, j+1) == 1) alive++;
        if (check(i, j-1) == 1) alive++;
        if (check(i, j+1) == 1) alive++;
        if (check(i+1, j) == 1) alive++;
        if (check(i+1, j-1) == 1) alive++;
        if (check(i+1, j+1) == 1) alive++;
        return alive;

    }
    public int check(int i, int j) {
        if (i<0 || i>=WIDTH || j<0 || j>=HEIGHT) return 0;
        else return grid[i][j];
    }

    public void setRectOn(Rectangle rect) {
        rect.setFill(Color.web("#2A9DF4"));
        rect.setStroke(Color.web("#03254c"));
        rect.setStrokeType(StrokeType.INSIDE);
        rect.setStrokeWidth(0.5);
    }
    public void setRectOff(Rectangle rect) {
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.WHITE);

    }
}
