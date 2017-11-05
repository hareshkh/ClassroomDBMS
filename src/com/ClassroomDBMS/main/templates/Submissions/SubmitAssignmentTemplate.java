package com.ClassroomDBMS.main.templates.Submissions;

import com.ClassroomDBMS.database.submissions.DBSubmissions;
import com.ClassroomDBMS.main.functions.profileStudent;
import com.ClassroomDBMS.main.models.AssignmentModel;
import com.ClassroomDBMS.main.templates.StudentCourse.StudentCourseTemplate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SubmitAssignmentTemplate {

    public static BorderPane getSubmitAssignmentView(AssignmentModel model, String studentEMailId, String courseId) {
        VBox vBox = new VBox(10);

        Label message = new Label(model.getAssignment_details());
        message.setWrapText(true);
        message.setFont(new Font("Cambria", 20));
        message.setTextFill(Color.web("#fff"));

        Label details = new Label(model.getTimestamp() + "\nDeadline : " + model.getDeadline() + "\n" + model.getAttachment_type() + " : " + model.getAttachment_url());
        details.setFont(new Font("Cambria", 16));
        details.setTextFill(Color.web("#fff"));

        TextField submission = new TextField();
        submission.setPromptText("Submit link to your submission");
        submission.setStyle("-fx-focus-color: transparent;");
        submission.setPrefHeight(35);

        Label result = new Label();
        result.setFont(new Font("Cambria", 18));
        result.setTextFill(Color.web("#fff"));

        Button saveButton = new Button("SAVE");
        saveButton.setFont(new Font("Cambria", 16));
        saveButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        saveButton.setTextFill(Color.web("#fff"));

        saveButton.setOnMouseClicked(e -> {
            String status = DBSubmissions.submitAssignment(model.getAssignmentId(),
                    studentEMailId,
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                    submission.getText());
            if (status.equals("success")) {
                result.setText("Submitted Successfully");
                profileStudent.optionData.setTop(StudentCourseTemplate.getStudentCourseDetailsView(studentEMailId, courseId));
            } else {
                result.setText("There was an error");
            }
        });

        vBox.getChildren().addAll(message, details, submission, result, saveButton);

        return new BorderPane(vBox);

    }

}
