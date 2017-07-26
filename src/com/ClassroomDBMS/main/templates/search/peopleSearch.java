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

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search by name, mail id, phone no., college .");
        searchBox.setStyle("-fx-focus-color: transparent;-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 100; -fx-text-inner-color: #fff;");
        searchBox.setPrefWidth(100);
        searchBox.setPrefHeight(35);
        searchBox.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                frame.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            VBox results = searchByKeyword.searchByKeyword(newValue);
            results.setPadding(new Insets(10,0,0,10));
            ScrollPane searchedStudents = new ScrollPane(results);
            searchedStudents.setFitToWidth(true);
            searchedStudents.setMaxHeight(main.window.getHeight()-170);
            main.window.heightProperty().addListener(ee-> searchedStudents.setMaxHeight(main.window.getHeight()-170));
            frame.setCenter(searchedStudents);
        });

        frame.setTop(searchBox);

        return  frame;
    }
}
