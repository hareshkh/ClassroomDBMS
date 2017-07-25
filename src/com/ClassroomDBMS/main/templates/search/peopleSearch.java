package com.ClassroomDBMS.main.templates.search;

import com.ClassroomDBMS.database.peopleSearch.searchByKeyword;
import com.ClassroomDBMS.main.windows.home.main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class peopleSearch {
    final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

    public BorderPane peoplesearch(){
        BorderPane frame = new BorderPane();
        frame.setPadding(new Insets(50,60,0,60));

        VBox searchVB = new VBox(15);
        searchVB.setAlignment(Pos.TOP_CENTER);
        searchVB.setPadding(new Insets(10,0,10,0));

        Label searchLabel = new Label("Find people: ");
        searchLabel.setTextFill(Color.web("#fff"));
        searchLabel.setFont(new Font("Cambria", 25));
        searchLabel.setTextFill(Color.web("#5a5a5a"));

        //=================================================================================
        HBox searchRow = new HBox(15);
        searchRow.setAlignment(Pos.TOP_CENTER);

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search by name, mail id, phone no., college .");
        searchBox.setStyle("-fx-focus-color: transparent;");
        searchBox.setPrefColumnCount(30);
        searchBox.setPrefHeight(35);
        searchBox.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                frame.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-focus-color: transparent;");
        searchButton.setFont(new Font("Cambria", 18));
        searchButton.setStyle("-fx-background-color: #6ac045;");
        searchButton.setTextFill(Color.web("#fff"));
        searchButton.setCursor(Cursor.HAND);

        searchRow.getChildren().addAll(searchBox,searchButton);

        searchVB.getChildren().addAll(searchLabel,searchRow);

        searchButton.setOnAction(e-> {
            VBox results = searchByKeyword.searchByKeyword(searchBox.getText());
            ScrollPane searchedStudents = new ScrollPane(results);
            searchedStudents.setFitToWidth(true);
            searchedStudents.setPrefViewportHeight(main.window.getHeight()-200);
            main.window.heightProperty().addListener(ee-> searchedStudents.setPrefViewportHeight(main.window.getHeight()-200));
            frame.setCenter(searchedStudents);
        });

        frame.setTop(searchVB);

        return  frame;
    }
}
