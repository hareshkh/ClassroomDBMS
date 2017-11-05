package com.ClassroomDBMS.main.templates.Submissions;

import com.ClassroomDBMS.database.assignments.DBAssignments;
import com.ClassroomDBMS.database.submissions.DBSubmissions;
import com.ClassroomDBMS.main.models.AssignmentModel;
import com.ClassroomDBMS.main.models.SubmissionModel;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ViewSubmissionsTemplate {

    public static BorderPane getAllSubmissionView(int assignmentId) {
        VBox vBox = new VBox(10);

        AssignmentModel model = DBAssignments.getAssignmentById(assignmentId);

        Label message = new Label(model.getAssignment_details());
        message.setWrapText(true);
        message.setFont(new Font("Cambria", 20));
        message.setTextFill(Color.web("#fff"));

        Label details = new Label(model.getTimestamp() + "\nDeadline : " + model.getDeadline() + "\n" + model.getAttachment_type() + " : " + model.getAttachment_url());
        details.setFont(new Font("Cambria", 16));
        details.setTextFill(Color.web("#fff"));

        vBox.getChildren().addAll(message, details);

        ArrayList<SubmissionModel> submissions = DBSubmissions.getSubmissions(assignmentId);

        for (SubmissionModel submissionModel : submissions) {
            Label label = new Label(submissionModel.getStudentEmailId() + "\n" + submissionModel.getSubmission() + "\n" + submissionModel.getTimestamp());
            label.setFont(new Font("Cambria", 18));
            label.setTextFill(Color.web("#fff"));

            vBox.getChildren().add(label);

        }

        return new BorderPane(vBox);

    }

}
