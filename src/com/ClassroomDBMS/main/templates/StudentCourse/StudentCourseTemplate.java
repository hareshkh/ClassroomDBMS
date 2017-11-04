package com.ClassroomDBMS.main.templates.StudentCourse;

import com.ClassroomDBMS.database.classrooms.DBClassrooms;
import com.ClassroomDBMS.main.models.ClassroomModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
        label.setFont(new Font("Cambria", 25));
        label.setTextFill(Color.web("#fff"));

        Button assignmentButton = new Button("View Assignments");
        assignmentButton.setFont(new Font("Cambria", 18));
        assignmentButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        assignmentButton.setTextFill(Color.web("#fff"));
        assignmentButton.setOnAction(e -> {
//            profileFaculty.optionData.setTop();
        });

        vBox.getChildren().addAll(label, assignmentButton);

        BorderPane borderPane = new BorderPane(vBox);
        return borderPane;
    }

}
