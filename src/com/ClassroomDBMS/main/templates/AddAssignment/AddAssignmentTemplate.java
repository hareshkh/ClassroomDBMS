package com.ClassroomDBMS.main.templates.AddAssignment;

import com.ClassroomDBMS.database.assignments.DBAssignments;
import com.ClassroomDBMS.database.classrooms.DBClassrooms;
import com.ClassroomDBMS.main.functions.profileFaculty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAssignmentTemplate {

    public static BorderPane getAddAssignmentView(String courseId, String facultyEmailId) {
        BorderPane borderPane = new BorderPane();

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        Label label = new Label("Add Assignment");
        label.setFont(new Font("Cambria", 20));
        label.setTextFill(Color.web("#fff"));

        TextField deadline = new TextField();
        deadline.setPromptText("Deadline");
        deadline.setStyle("-fx-focus-color: transparent;");
        deadline.setPrefHeight(35);

        TextField assignmentDetails = new TextField();
        assignmentDetails.setPromptText("Assignment Details");
        assignmentDetails.setStyle("-fx-focus-color: transparent;");
        assignmentDetails.setPrefHeight(35);

        TextField attachment_type = new TextField();
        attachment_type.setPromptText("Attachment Type");
        attachment_type.setStyle("-fx-focus-color: transparent;");
        attachment_type.setPrefHeight(35);

        TextField attachment_url = new TextField();
        attachment_url.setPromptText("Attachment URL");
        attachment_url.setStyle("-fx-focus-color: transparent;");
        attachment_url.setPrefHeight(35);

        vBox.getChildren().addAll(label, deadline, assignmentDetails, attachment_type, attachment_url);

        HBox saveRow = new HBox();
        Button saveButton = new Button("Add");
        saveButton.setFont(new Font("Cambria", 18));
        saveButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        saveButton.setTextFill(Color.web("#fff"));
        saveButton.setOnAction(e -> {

            DBAssignments.postAssignment(courseId, facultyEmailId,
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                    deadline.getText(),
                    assignmentDetails.getText(),
                    attachment_type.getText(),
                    attachment_url.getText());

        });
        saveRow.getChildren().addAll(saveButton);
        saveRow.setAlignment(Pos.BASELINE_CENTER);

        borderPane.setTop(vBox);
        borderPane.setBottom(saveRow);

        return borderPane;
    }

}
