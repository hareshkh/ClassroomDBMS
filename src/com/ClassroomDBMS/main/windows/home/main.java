package com.ClassroomDBMS.main.windows.home;

import com.ClassroomDBMS.database.logIn.userLoggedIn;
import com.ClassroomDBMS.main.functions.getMotherboardSN;
import com.ClassroomDBMS.main.functions.loginHome;

import com.ClassroomDBMS.main.functions.profileFaculty;
import com.ClassroomDBMS.main.functions.profileStudent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class main extends Application {
    public static Stage window;
    public static Scene scene;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Classroom DBMS");

        String userID = getMotherboardSN.getMotherboardSN();
        String[] status = userLoggedIn.userLoggedIn(userID);

        if (!status[0].equals("success"))
            window.setScene(loginHome.homeView());
        else {
            if (status[1].equals("student")) {
                window.setScene(profileStudent.main(status));
            } else if (status[1].equals("faculty")) {
                window.setScene(profileFaculty.main(status));
            }
        }

        window.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/ClassroomDBMS.png")));

        window.setMinWidth(850);
        window.setMinHeight(535);
        window.show();
        window.setOnCloseRequest(e -> {
            System.exit(0);
        });

    }
}