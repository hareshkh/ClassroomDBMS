package com.ClassroomDBMS.main.templates.tutorialSubmission;

import com.ClassroomDBMS.database.tutorialSubmission.addNewTutorial;
import com.ClassroomDBMS.database.tutorialSubmission.fetchTutorials;
import com.ClassroomDBMS.main.windows.home.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;

public class submissions {

    public static ScrollPane scroller;
    public static BorderPane tutorialProfile;
    public static BorderPane tutorials;

    public static BorderPane tutorials(String emailId){
        VBox fetchedtutorials = new VBox(15);

        tutorialProfile = new BorderPane();
        tutorialProfile.setPadding(new Insets(30,60,0,60));

        Label header = new Label("Classroom DBMS Submissions");
        header.setAlignment(Pos.TOP_CENTER);
        header.setFont(new Font("Cambria", 32));
        header.setTextFill(Color.web("#ededed"));

        BorderPane headerTitle = new BorderPane(header,null,null,null,null);
        headerTitle.setPadding(new Insets(0,0,30,0));

        tutorialProfile.setTop(headerTitle);

        tutorials = new BorderPane();
        tutorials.setStyle("-fx-background-color: transparent");
        tutorials.setPrefHeight(main.window.getHeight()-220);
        main.window.heightProperty().addListener(e-> tutorials.setPrefHeight(main.window.getHeight()-220));

        fetchedtutorials.getChildren().add(fetchTutorials.fetchTutorials(emailId));

        tutorials.setTop(fetchedtutorials);

        scroller = new ScrollPane(tutorials);
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(fetchedtutorials.heightProperty());

        tutorialProfile.setCenter(scroller);

        BorderPane mytutorialCorner = new BorderPane();
        mytutorialCorner.setPadding(new Insets(15,0,0,0));

        TextArea newtutorial = new TextArea();
        newtutorial.setPromptText("Enter your tutorial here");
        newtutorial.setStyle("-fx-focus-color: transparent; -fx-border-color: #fff;-fx-border-width: 1 1 1 0;-fx-padding: 0 0 0 -2;");
        newtutorial.setPrefHeight(50);
        newtutorial.setWrapText(true);
        newtutorial.setPrefWidth(main.window.getWidth()-400);
        main.window.widthProperty().addListener(e-> newtutorial.setPrefWidth(main.window.getWidth()-400));

        Button send = new Button("Send");
        send.setPrefHeight(50);
        send.setFont(new Font("Cambria", 16));
        send.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent; ; -fx-border: 0");
        send.setTextFill(Color.web("#fff"));
        send.setCursor(Cursor.HAND);

        Label error = new Label("");
        error.setTextFill(Color.web("red"));

        send.setOnAction(e-> {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String status = addNewTutorial.add(timeStamp, fetchTutorials.latestestTut, emailId, newtutorial.getText());

            if (status=="success"){
                BorderPane addnewtutorial = message.rightformatmessage(timeStamp, newtutorial.getText());
                fetchedtutorials.getChildren().add(addnewtutorial);
                newtutorial.setText("");
            }
            else
                error.setText(status);
        });

        mytutorialCorner.setLeft(newtutorial);
        mytutorialCorner.setRight(send);
        mytutorialCorner.setBottom(error);

        tutorialProfile.setBottom(mytutorialCorner);

        return tutorialProfile;

    }

}
