package com.ClassroomDBMS.main.models;

import com.ClassroomDBMS.main.functions.profileFaculty;
import com.ClassroomDBMS.main.templates.Announcement.AddAnnouncementTemplate;
import com.ClassroomDBMS.main.templates.Announcement.ViewAllAnnouncementsTemplate;
import com.ClassroomDBMS.main.templates.Assignment.AddAssignmentTemplate;
import com.ClassroomDBMS.main.templates.Assignment.ViewAllAssignmentsTemplate;
import com.ClassroomDBMS.main.windows.home.main;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ClassroomModel {

    private String courseId;
    private String faculty_emailId;
    private String course_name;
    private String lecture_timing;
    private String tutorial_timing;
    private String marks_distribution;
    private String attendance_rule;
    private String grading_rule;
    private String lecture_hall;
    private String literature_link;

    public ClassroomModel(String courseId, String faculty_emailId, String course_name, String lecture_timing, String tutorial_timing, String marks_distribution, String attendance_rule, String grading_rule, String lecture_hall, String literature_link) {
        this.courseId = courseId;
        this.faculty_emailId = faculty_emailId;
        this.course_name = course_name;
        this.lecture_timing = lecture_timing;
        this.tutorial_timing = tutorial_timing;
        this.marks_distribution = marks_distribution;
        this.attendance_rule = attendance_rule;
        this.grading_rule = grading_rule;
        this.lecture_hall = lecture_hall;
        this.literature_link = literature_link;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getFaculty_emailId() {
        return faculty_emailId;
    }

    public void setFaculty_emailId(String faculty_emailId) {
        this.faculty_emailId = faculty_emailId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLecture_timing() {
        return lecture_timing;
    }

    public void setLecture_timing(String lecture_timing) {
        this.lecture_timing = lecture_timing;
    }

    public String getTutorial_timing() {
        return tutorial_timing;
    }

    public void setTutorial_timing(String tutorial_timing) {
        this.tutorial_timing = tutorial_timing;
    }

    public String getMarks_distribution() {
        return marks_distribution;
    }

    public void setMarks_distribution(String marks_distribution) {
        this.marks_distribution = marks_distribution;
    }

    public String getAttendance_rule() {
        return attendance_rule;
    }

    public void setAttendance_rule(String attendance_rule) {
        this.attendance_rule = attendance_rule;
    }

    public String getGrading_rule() {
        return grading_rule;
    }

    public void setGrading_rule(String grading_rule) {
        this.grading_rule = grading_rule;
    }

    public String getLecture_hall() {
        return lecture_hall;
    }

    public void setLecture_hall(String lecture_hall) {
        this.lecture_hall = lecture_hall;
    }

    public String getLiterature_link() {
        return literature_link;
    }

    public void setLiterature_link(String literature_link) {
        this.literature_link = literature_link;
    }

    public BorderPane getInfo() {

        VBox vBox = new VBox(10);

        String result = "";

        result += "Course ID : " + courseId + "\n";
        result += "Course Name : " + course_name + "\n";
        result += "Faculty Email : " + faculty_emailId + "\n";
        result += "Lecture Timing : " + lecture_timing + "\n";
        result += "Tutorial Timing : " + tutorial_timing + "\n";
        result += "Marks Distribution : " + marks_distribution + "\n";
        result += "Attendance Rule : " + attendance_rule + "\n";
        result += "Grading Rule : " + grading_rule + "\n";
        result += "Lecture Hall : " + lecture_hall + "\n";
        result += "Literature Link : " + literature_link + "\n";

        Label label = new Label(result);
        label.setFont(new Font("Cambria", 25));
        label.setTextFill(Color.web("#fff"));

        HBox hBox = new HBox(10);

        Button assignmentButton = new Button("Add Assignment");
        assignmentButton.setFont(new Font("Cambria", 18));
        assignmentButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        assignmentButton.setTextFill(Color.web("#fff"));
        assignmentButton.setOnAction(e -> {
            profileFaculty.optionData.setTop(AddAssignmentTemplate.getAddAssignmentView(courseId, faculty_emailId));
        });

        Button announcementButton = new Button("Add Announcement");
        announcementButton.setFont(new Font("Cambria", 18));
        announcementButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        announcementButton.setTextFill(Color.web("#fff"));
        announcementButton.setOnAction(e -> {
            profileFaculty.optionData.setTop(AddAnnouncementTemplate.getAddAnnouncementView(courseId, faculty_emailId));
        });

        hBox.getChildren().addAll(assignmentButton, announcementButton);

        HBox hBox1 = new HBox(10);

        Button allAssignmentButton = new Button("View All Assignment");
        allAssignmentButton.setFont(new Font("Cambria", 18));
        allAssignmentButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        allAssignmentButton.setTextFill(Color.web("#fff"));
        allAssignmentButton.setOnAction(e -> {
            profileFaculty.optionData.setTop(ViewAllAssignmentsTemplate.getAllAssignmentsView(courseId, faculty_emailId));
        });

        Button allAnnouncementButton = new Button("View All Announcement");
        allAnnouncementButton.setFont(new Font("Cambria", 18));
        allAnnouncementButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        allAnnouncementButton.setTextFill(Color.web("#fff"));
        allAnnouncementButton.setOnAction(e -> {
            profileFaculty.optionData.setTop(ViewAllAnnouncementsTemplate.getAllAnnouncementsView(courseId, faculty_emailId));
        });

        hBox1.getChildren().addAll(allAssignmentButton, allAnnouncementButton);

        vBox.getChildren().addAll(label, hBox, hBox1);

        BorderPane borderPane = new BorderPane(vBox);

        main.window.heightProperty().addListener(e -> label.setMinHeight(0.8 * main.window.getHeight()));
        borderPane.setPadding(new Insets(20));

        return borderPane;
    }

}
