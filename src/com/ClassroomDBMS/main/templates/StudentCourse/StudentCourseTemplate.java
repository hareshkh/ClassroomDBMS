package com.ClassroomDBMS.main.templates.StudentCourse;

import com.ClassroomDBMS.database.announcements.DBAnnouncements;
import com.ClassroomDBMS.database.assignments.DBAssignments;
import com.ClassroomDBMS.database.classrooms.DBClassrooms;
import com.ClassroomDBMS.main.functions.profileStudent;
import com.ClassroomDBMS.main.models.AnnouncementModel;
import com.ClassroomDBMS.main.models.AssignmentModel;
import com.ClassroomDBMS.main.models.ClassroomModel;
import com.ClassroomDBMS.main.templates.Submissions.SubmitAssignmentTemplate;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class StudentCourseTemplate {

    public static BorderPane getStudentCourseDetailsView(String studentEmailId, String courseId) {

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        ClassroomModel classroomModel = DBClassrooms.getClassroomById(courseId);

        String result = "";

        result += "Course ID : " + classroomModel.getCourseId() + "\n";
        result += "Course Name : " + classroomModel.getCourse_name() + "\n";
        result += "Faculty Email : " + classroomModel.getFaculty_emailId() + "\n";
        result += "Lecture Timing : " + classroomModel.getLecture_timing() + "\n";
        result += "Tutorial Timing : " + classroomModel.getTutorial_timing() + "\n";
        result += "Marks Distribution : " + classroomModel.getMarks_distribution() + "\n";
        result += "Attendance Rule : " + classroomModel.getAttendance_rule() + "\n";
        result += "Grading Rule : " + classroomModel.getGrading_rule() + "\n";
        result += "Lecture Hall : " + classroomModel.getLecture_hall() + "\n";
        result += "Literature Link : " + classroomModel.getLiterature_link() + "\n";

        Label label = new Label(result);
        label.setFont(new Font("Cambria", 15));
        label.setTextFill(Color.web("#fff"));

        HBox assignmentAnnouncementContainer = new HBox(10);

        VBox assignmentsContainer = new VBox(10);

        Label assignmentLabel = new Label("Assignments");
        assignmentLabel.setFont(new Font("Cambria", 18));
        assignmentLabel.setTextFill(Color.web("#fff"));

        assignmentsContainer.getChildren().add(assignmentLabel);

        ArrayList<AssignmentModel> assignmentModels = DBAssignments.getAllAssignments(classroomModel.getCourseId(), classroomModel.getFaculty_emailId());

        for (AssignmentModel model : assignmentModels) {
            Label message = new Label(model.getAssignment_details());
            message.setWrapText(true);
            message.setFont(new Font("Cambria", 20));
            message.setTextFill(Color.web("#fff"));

            Label details = new Label(model.getTimestamp() + "\nDeadline : " + model.getDeadline() + "\n" + model.getAttachment_type() + " : " + model.getAttachment_url());
            details.setFont(new Font("Cambria", 16));
            details.setTextFill(Color.web("#fff"));

            Button submitButton = new Button("Submit");
            submitButton.setFont(new Font("Cambria", 16));
            submitButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
            submitButton.setTextFill(Color.web("#fff"));

            submitButton.setOnMouseClicked(e -> {
                profileStudent.optionData.setTop(SubmitAssignmentTemplate.getSubmitAssignmentView(model, studentEmailId, courseId));
            });

            assignmentsContainer.getChildren().addAll(message, details, submitButton);
        }

        ScrollPane sc = new ScrollPane();
        sc.setContent(assignmentsContainer);

        VBox announcementsContainer = new VBox(10);

        Label announcementLabel = new Label("Announcements");
        announcementLabel.setFont(new Font("Cambria", 18));
        announcementLabel.setTextFill(Color.web("#fff"));

        announcementsContainer.getChildren().add(announcementLabel);

        ArrayList<AnnouncementModel> announcementModels = DBAnnouncements.getAllAnnouncements(classroomModel.getCourseId(), classroomModel.getFaculty_emailId());

        for (AnnouncementModel model : announcementModels) {
            Label message = new Label(model.getMessage());
            message.setWrapText(true);
            message.setFont(new Font("Cambria", 18));
            message.setTextFill(Color.web("#fff"));

            Label details = new Label(model.getTimestamp() + "\n" + model.getAttachment_type() + " : " + model.getAttachment_url());
            details.setFont(new Font("Cambria", 16));
            details.setTextFill(Color.web("#fff"));

            announcementsContainer.getChildren().addAll(message, details);
        }

        ScrollPane sc1 = new ScrollPane();
        sc1.setContent(announcementsContainer);

        assignmentAnnouncementContainer.getChildren().addAll(sc, sc1);

        vBox.getChildren().addAll(label, assignmentAnnouncementContainer);

        BorderPane borderPane = new BorderPane(vBox);
        return borderPane;
    }

}
