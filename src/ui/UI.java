package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UI {

    private double xOffset = 0;
    private double yOffset = 0;

    public UI() throws Exception{
        Stage settingsStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UI_FXML.fxml"));
        settingsStage.setTitle("Hello World");
        settingsStage.setScene(new Scene(root, 300, 275));
        settingsStage.initStyle(StageStyle.UNDECORATED);


        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                settingsStage.setX(event.getScreenX() - xOffset);
                settingsStage.setY(event.getScreenY() - yOffset);
            }
        });

        settingsStage.show();
    }

}
