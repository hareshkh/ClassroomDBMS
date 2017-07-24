package com.ClassroomDBMS.main.functions;

import com.ClassroomDBMS.main.windows.login.userLogin;
import com.ClassroomDBMS.main.windows.signUp.userSignUp;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class loginHome {

    public static Scene homeView(){

        BorderPane headerlayout = new BorderPane();
        headerlayout.setPadding(new Insets(20,30,0,30));

        HBox headerVB = new HBox(10);
        headerVB.setPadding(new Insets(6,10,0,0));
        headerVB.setAlignment(Pos.BASELINE_RIGHT);

        Label login = GlyphsDude.createIconLabel( FontAwesomeIcon.SIGN_IN,
                "Login |",
                "16",
                "18",
                ContentDisplay.LEFT );
        login.setFont(new Font("Cambria", 20));
        login.setTextFill(Color.web("#ededed"));

        Label register = GlyphsDude.createIconLabel( FontAwesomeIcon.USER_PLUS,
                "Register",
                "16",
                "18",
                ContentDisplay.LEFT );
        register.setFont(new Font("Cambria", 20));
        register.setTextFill(Color.web("#ededed"));

        login.setOnMouseClicked(e->{
            userLogin ob = new userLogin();
            ob.userLogin();
        });

        register.setOnMouseClicked(e->{
            userSignUp ob = new userSignUp();
            ob.userSignUp();
        });

        headerVB.getChildren().addAll(login,register);
        headerlayout.setTop(headerVB);

        Label title = new Label("Classroom DBMS");
        title.setFont(new Font("Cambria", 60));
        title.setTextFill(Color.web("#ededed"));

        Label helpLine = new Label("Login to continue !!");
        helpLine.setFont(new Font("Cambria", 20));
        helpLine.setTextFill(Color.web("#ededed"));

        VBox centerVB = new VBox(30);
        centerVB.setAlignment(Pos.CENTER);
        centerVB.getChildren().addAll(title, helpLine);

        headerlayout.setCenter(centerVB);

        Scene scene = new Scene(headerlayout,800,500);
        scene.getStylesheets().add(loginHome.class.getResource("../resources/css/main.css").toExternalForm());

        return  scene;
    }
}
