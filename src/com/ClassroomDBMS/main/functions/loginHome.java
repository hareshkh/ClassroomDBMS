package com.ClassroomDBMS.main.functions;

import com.ClassroomDBMS.main.windows.login.userLogin;
import com.ClassroomDBMS.main.windows.signUp.facultySignUp;

import com.ClassroomDBMS.main.windows.signUp.studentSignUp;
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

    public  static String image;

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

        Label registerFaculty = GlyphsDude.createIconLabel( FontAwesomeIcon.USER_PLUS,
                "Register Faculty |",
                "16",
                "18",
                ContentDisplay.LEFT );
        registerFaculty.setFont(new Font("Cambria", 20));
        registerFaculty.setTextFill(Color.web("#ededed"));

        Label registerStudent = GlyphsDude.createIconLabel( FontAwesomeIcon.USER_PLUS,
                "Register Student",
                "16",
                "18",
                ContentDisplay.LEFT );
        registerStudent.setFont(new Font("Cambria", 20));
        registerStudent.setTextFill(Color.web("#ededed"));

        login.setOnMouseClicked(e->{
            userLogin ob = new userLogin();
            ob.userLogin();
        });

        registerFaculty.setOnMouseClicked(e->{
            facultySignUp ob = new facultySignUp();
            ob.signUp();
        });

        registerStudent.setOnMouseClicked(e->{
            studentSignUp ob =  new studentSignUp();
            ob.signUp();
        });

        headerVB.getChildren().addAll(login,registerFaculty, registerStudent);
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

        image = loginHome.class.getResource("../resources/images/splash.jpg").toExternalForm();
        headerlayout.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        return  scene;
    }
}
