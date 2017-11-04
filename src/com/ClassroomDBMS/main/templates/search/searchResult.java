package com.ClassroomDBMS.main.templates.search;

import com.ClassroomDBMS.main.windows.home.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class searchResult {

    public static BorderPane searchResult(String firstName, String lastName, String emailId, String phoneNumber, String college) {

        BorderPane results = new BorderPane();
        results.setStyle("-fx-background-color: #171717");
        results.setPadding(new Insets(10, 0, 0, 0));

        Label resultLabel = new Label(firstName + " " + lastName);
        resultLabel.setFont(new Font("Cambria", 20));
        resultLabel.setTextFill(Color.web("#fff"));
        resultLabel.setPadding(new Insets(10));
        resultLabel.setPrefWidth(0.2 * main.window.getWidth());
        resultLabel.setWrapText(true);
        main.window.widthProperty().addListener(e -> resultLabel.setPrefWidth(0.2 * main.window.getWidth()));

        Label emailIdLabel = new Label(emailId);
        emailIdLabel.setFont(new Font("Cambria", 20));
        emailIdLabel.setTextFill(Color.web("#fff"));
        emailIdLabel.setPadding(new Insets(10));
        emailIdLabel.setPrefWidth(0.2 * main.window.getWidth());
        emailIdLabel.setWrapText(true);
        main.window.widthProperty().addListener(e -> emailIdLabel.setPrefWidth(0.2 * main.window.getWidth()));

        Label phoneNumberLabel = new Label(phoneNumber);
        phoneNumberLabel.setFont(new Font("Cambria", 20));
        phoneNumberLabel.setTextFill(Color.web("#fff"));
        phoneNumberLabel.setPadding(new Insets(10));
        phoneNumberLabel.setPrefWidth(0.15 * main.window.getWidth());
        phoneNumberLabel.setWrapText(true);
        main.window.widthProperty().addListener(e -> phoneNumberLabel.setPrefWidth(0.15 * main.window.getWidth()));

        Label collegeLabel = new Label(college);
        collegeLabel.setFont(new Font("Cambria", 20));
        collegeLabel.setTextFill(Color.web("#fff"));
        collegeLabel.setPadding(new Insets(10));
        collegeLabel.setPrefWidth(0.15 * main.window.getWidth());
        collegeLabel.setWrapText(true);
        main.window.widthProperty().addListener(e -> collegeLabel.setPrefWidth(0.15 * main.window.getWidth()));

        HBox result = new HBox(0);
        result.setAlignment(Pos.TOP_LEFT);
        result.getChildren().addAll(resultLabel, emailIdLabel, phoneNumberLabel, collegeLabel);

        results.setLeft(result);
        return results;
    }

    public static StackPane searchResult() {

        StackPane results = new StackPane();
        results.setStyle("-fx-background-color: #171717");
        results.setPadding(new Insets(10, 0, 10, 0));

        Label result = new Label("Sorry. No Results found on DB search");
        result.setFont(new Font("Cambria", 20));
        result.setTextFill(Color.web("#fff"));
        result.setPadding(new Insets(10));

        results.getChildren().add(result);
        return results;
    }
}
