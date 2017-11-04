package com.ClassroomDBMS.main.templates.AddCourse;

import com.ClassroomDBMS.database.classrooms.DBClassrooms;
import com.ClassroomDBMS.main.functions.profileFaculty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AddCourseTemplate {

    public static BorderPane getAddCourseView(String facultyEmailId) {

        BorderPane borderPane = new BorderPane();

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        TextField courseId = new TextField();
        courseId.setPromptText("Course ID");
        courseId.setStyle("-fx-focus-color: transparent;");
        courseId.setPrefHeight(35);

        TextField courseName = new TextField();
        courseName.setPromptText("Course Name");
        courseName.setStyle("-fx-focus-color: transparent;");
        courseName.setPrefHeight(35);

        TextField lectureTiming = new TextField();
        lectureTiming.setPromptText("Lecture Timing");
        lectureTiming.setStyle("-fx-focus-color: transparent;");
        lectureTiming.setPrefHeight(35);

        TextField tutorialTiming = new TextField();
        tutorialTiming.setPromptText("Tutorial Timing");
        tutorialTiming.setStyle("-fx-focus-color: transparent;");
        tutorialTiming.setPrefHeight(35);

        TextField marksDistribution = new TextField();
        marksDistribution.setPromptText("Marks Distribution");
        marksDistribution.setStyle("-fx-focus-color: transparent;");
        marksDistribution.setPrefHeight(35);

        TextField attendanceRule = new TextField();
        attendanceRule.setPromptText("Attendance Rule");
        attendanceRule.setStyle("-fx-focus-color: transparent;");
        attendanceRule.setPrefHeight(35);

        TextField gradingRule = new TextField();
        gradingRule.setPromptText("Grading Rule");
        gradingRule.setStyle("-fx-focus-color: transparent;");
        gradingRule.setPrefHeight(35);

        TextField lectureHall = new TextField();
        lectureHall.setPromptText("Lecture Hall");
        lectureHall.setStyle("-fx-focus-color: transparent;");
        lectureHall.setPrefHeight(35);

        TextField literatureLink = new TextField();
        literatureLink.setPromptText("Literature Link");
        literatureLink.setStyle("-fx-focus-color: transparent;");
        literatureLink.setPrefHeight(35);

        vBox.getChildren().addAll(courseId, courseName, lectureTiming,
                tutorialTiming, marksDistribution, attendanceRule,
                gradingRule, lectureHall, literatureLink);

        HBox saveRow = new HBox();
        Button saveButton = new Button("Save");
        saveButton.setFont(new Font("Cambria", 18));
        saveButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        saveButton.setTextFill(Color.web("#fff"));
        saveButton.setOnAction(e -> {
            DBClassrooms.newClassroom(courseId.getText(), facultyEmailId, courseName.getText(),
                    lectureTiming.getText(), tutorialTiming.getText(), marksDistribution.getText(),
                    attendanceRule.getText(), gradingRule.getText(),
                    lectureHall.getText(), literatureLink.getText());

            profileFaculty.updateCourses();

        });
        saveRow.getChildren().addAll(saveButton);
        saveRow.setAlignment(Pos.BASELINE_CENTER);

        borderPane.setTop(vBox);
        borderPane.setBottom(saveRow);

        return borderPane;

    }

}
