package com.ClassroomDBMS.main.templates.Assignment;

import com.ClassroomDBMS.database.assignments.DBAssignments;
import com.ClassroomDBMS.main.functions.profileFaculty;
import com.ClassroomDBMS.main.models.AssignmentModel;
import com.ClassroomDBMS.main.templates.Submissions.ViewSubmissionsTemplate;
import com.ClassroomDBMS.main.windows.home.main;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ViewAllAssignmentsTemplate {

    public static BorderPane getAllAssignmentsView(String courseId, String faculty_emailId) {
        ArrayList<AssignmentModel> assignmentModels = DBAssignments.getAllAssignments(courseId, faculty_emailId);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        Label courseLabel = new Label(courseId);
        courseLabel.setFont(new Font("Cambria", 35));
        courseLabel.setTextFill(Color.web("#fff"));

        vBox.getChildren().add(courseLabel);

        for (AssignmentModel model : assignmentModels) {
            Label message = new Label(model.getAssignment_details());
            message.setWrapText(true);
            message.setFont(new Font("Cambria", 20));
            message.setTextFill(Color.web("#fff"));

            Label details = new Label(model.getTimestamp() + "\nDeadline : " + model.getDeadline() + "\n" + model.getAttachment_type() + " : " + model.getAttachment_url());
            details.setFont(new Font("Cambria", 16));
            details.setTextFill(Color.web("#fff"));

            Button viewSubmissions = new Button("View Submissions");
            viewSubmissions.setFont(new Font("Cambria", 16));
            viewSubmissions.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
            viewSubmissions.setTextFill(Color.web("#fff"));

            viewSubmissions.setOnMouseClicked(e -> {
                profileFaculty.optionData.setTop(ViewSubmissionsTemplate.getAllSubmissionView(model.getAssignmentId()));
            });

            vBox.getChildren().addAll(message, details, viewSubmissions);

        }

        ScrollPane sc = new ScrollPane();
        sc.setContent(vBox);
        sc.setFitToWidth(true);
        sc.setMaxHeight(main.window.getHeight() - 50);
        main.window.heightProperty().addListener(e -> sc.setMaxHeight(main.window.getHeight() - 50));

        return new BorderPane(sc);
    }

}
