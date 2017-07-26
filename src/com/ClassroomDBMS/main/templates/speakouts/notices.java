package com.ClassroomDBMS.main.templates.speakouts;

import com.ClassroomDBMS.main.windows.home.main;
import com.ClassroomDBMS.database.speakOutMessages.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;

public class notices {

    public static ScrollPane scroller;
    public static BorderPane messageProfile;
    public static BorderPane messages;

    public static BorderPane notices(String emailId){

        messageProfile = new BorderPane();
        messageProfile.setPadding(new Insets(30,60,0,60));

        Label header = new Label("Classroom DBMS Announcements");
        header.setAlignment(Pos.TOP_CENTER);
        header.setFont(new Font("Cambria", 32));
        header.setTextFill(Color.web("#ededed"));

        TextField searchNotice = new TextField();
        searchNotice.setPromptText("Search by keyword");
        searchNotice.setPrefColumnCount(10);
        searchNotice.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        BorderPane headerTitle = new BorderPane(header,null,searchNotice,null,null);
        headerTitle.setPadding(new Insets(0,0,30,0));

        messageProfile.setTop(headerTitle);

        messages = new BorderPane();
        messages.setStyle("-fx-background-color: transparent");
        messages.setPrefHeight(main.window.getHeight()-220);
        main.window.heightProperty().addListener(e-> messages.setPrefHeight(main.window.getHeight()-220));

        VBox fetchedMessages = new VBox(15);
        fetchedMessages.getChildren().add(fetchLatest.fetchlatest(emailId));

        messages.setTop(fetchedMessages);

        scroller = new ScrollPane(messages);
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(fetchedMessages.heightProperty());

        messageProfile.setCenter(scroller);

        BorderPane mymessageCorner = new BorderPane();
        mymessageCorner.setPadding(new Insets(15,0,0,0));

        TextArea newMessage = new TextArea();
        newMessage.setPromptText("Enter your message here");
        newMessage.setStyle("-fx-focus-color: transparent; -fx-border-color: #fff;-fx-border-width: 1 1 1 0;-fx-padding: 0 0 0 -2;");
        newMessage.setPrefHeight(50);
        newMessage.setWrapText(true);
        newMessage.setPrefWidth(main.window.getWidth()-400);
        main.window.widthProperty().addListener(e-> newMessage.setPrefWidth(main.window.getWidth()-400));

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
            String status = addNewMessage.add(timeStamp, emailId,newMessage.getText());

            if (status=="success"){
                BorderPane newmessage = message.rightformatmessage(timeStamp, newMessage.getText());
                fetchedMessages.getChildren().add(newmessage);
                newMessage.setText("");
            }
            else
                error.setText(status);
        });

        mymessageCorner.setLeft(newMessage);
        mymessageCorner.setRight(send);
        mymessageCorner.setBottom(error);

        messageProfile.setBottom(mymessageCorner);

        return messageProfile;

    }
}
