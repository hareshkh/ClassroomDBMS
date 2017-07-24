package com.ClassroomDBMS.main.functions;

import com.ClassroomDBMS.main.windows.home.main;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class profile {

    public static Scene main(String[] profileDetails){
        BorderPane profileView = new BorderPane();

        Label logged = new Label("logged");
        logged.setStyle("-fx-text-fill: red");
        profileView.setCenter(logged);

        Scene scene = new Scene(profileView,800,500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());
        return scene;
    }
}
