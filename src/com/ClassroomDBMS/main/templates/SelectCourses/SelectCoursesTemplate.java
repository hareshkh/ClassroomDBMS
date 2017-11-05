package com.ClassroomDBMS.main.templates.SelectCourses;

import com.ClassroomDBMS.database.classrooms.DBClassrooms;
import com.ClassroomDBMS.database.studentclassrooms.DBStudentClassrooms;
import com.ClassroomDBMS.main.functions.profileStudent;
import com.ClassroomDBMS.main.models.ClassroomModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class SelectCoursesTemplate {

    public static BorderPane getSelectCourseView(String studentEmailId) {
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        ArrayList<ClassroomModel> classroomModels = DBClassrooms.getAllNonSelectedClassrooms(studentEmailId);

        for (ClassroomModel model : classroomModels) {
            Label courseId = new Label(model.getCourseId());
            courseId.setFont(new Font("Cambria", 20));
            courseId.setTextFill(Color.web("#fff"));

            Label details = new Label(model.getCourse_name() + "\n" + model.getFaculty_emailId());
            details.setFont(new Font("Cambria", 16));
            details.setTextFill(Color.web("#fff"));

            Button selectCourse = new Button("Select");
            selectCourse.setFont(new Font("Cambria", 16));
            selectCourse.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
            selectCourse.setTextFill(Color.web("#fff"));

            selectCourse.setOnMouseClicked(e -> {
                DBStudentClassrooms.newStudentClassroom(studentEmailId, model.getCourseId());
                profileStudent.updateCourses();
            });

            vBox.getChildren().addAll(courseId, details, selectCourse);
        }

        BorderPane borderPane = new BorderPane(vBox);

        return borderPane;
    }

}
