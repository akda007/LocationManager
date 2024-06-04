package com.locationmanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        Scene scene = StateListController.createScene();
        mainStage.setScene(scene);
        mainStage.setTitle("States List");
        mainStage.show();
    }
}
