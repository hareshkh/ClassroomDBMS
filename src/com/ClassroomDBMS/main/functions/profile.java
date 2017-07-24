package com.ClassroomDBMS.main.functions;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class profile {

    public static Scene main(String[] profileDetails){
        BorderPane profileView = new BorderPane();

        Scene scene = new Scene(profileView);
        scene.getStylesheets().add(profile.class.getResource("../resources/css/main.css").toExternalForm());
        return  scene;
    }
}
