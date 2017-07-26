package com.ClassroomDBMS.main.templates.Home;

import com.ClassroomDBMS.main.windows.home.main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class courseInfo {

    public static BorderPane TAinfo(){

        VBox profilevb = new VBox(20);
        profilevb.setAlignment(Pos.TOP_LEFT);

        Label courseTitle = new Label("DBMS (CSN-351) Autumn 2017-18");
        courseTitle.setFont(new Font("Cambria", 25));
        courseTitle.setTextFill(Color.web("#fff"));

        Label firstClass = new Label("First Class: 17-JUL-2017");
        firstClass.setFont(new Font("Cambria", 16));
        firstClass.setTextFill(Color.web("#fff"));

        Label lecturesTut = new Label("Lectures: MON(14:00 - 14:55), TUE(14:00 - 14:55), THU(14:00 - 14:55)\n" +
                "Tutorials: WED(11:05 - 12:00), WED(12:05 - 13:00)");
        lecturesTut.setFont(new Font("Cambria", 16));
        lecturesTut.setTextFill(Color.web("#fff"));

        Label totalmarks = new Label("Total Marks (100) = CWS (25) + MTE (25) + ETE (50)\n" +
                "CWS Breakup: Tutorials (8) + Class Tests (5+5) + Project (7)\n" +
                "Class Tests Dates (probable): 04-SEP-2017, 09-OCT-2017, 30-OCT-2017 (Best 2 marks out of 3 will be considered)");
        totalmarks.setFont(new Font("Cambria", 16));
        totalmarks.setTextFill(Color.web("#fff"));

        Label attendance = new Label("Attendance: 75% (Everyone will be allowed to sit for MTE. You will be allowed to sit for ETE if and only if you have 75% overall attendance in the course or you are within top 30% students of the course in terms of your cumulative performance at the time of attendance calculation.)");
        attendance.setWrapText(true);
        attendance.setFont(new Font("Cambria", 16));
        attendance.setTextFill(Color.web("#fff"));

        Label bonus = new Label("Grade Calculation: Automatic via GFS. No change request of grade boundaries will be entertained.\n" +
                "Current state of marks and attendance will be available via shared google sheet.\n" +
                "Bonus mark (if any awarded during random quizzes) will only be used in case you fall on the grade boundary. No student can obtain more than 1 bonus mark.");
        bonus.setWrapText(true);
        bonus.setFont(new Font("Cambria", 16));
        bonus.setTextFill(Color.web("#fff"));

        Label room = new Label("Room\tLectures: LHC-204, Tutorials: W-201 (EC Building)");
        room.setFont(new Font("Cambria", 16));
        room.setTextFill(Color.web("#fff"));

        Label books = new Label("Books\tUllman Database Systems the Complete Book\n" +
                "     \tNavathe Fundamentals of Database Systems 6thEd\n" +
                "     \tKorth Database System Concepts 6thEd\n" +
                "     \tRamakrishnan Database Management Systems 3rdEd");
        books.setFont(new Font("Cambria", 16));
        books.setTextFill(Color.web("#fff"));

        profilevb.getChildren().addAll(courseTitle,firstClass,lecturesTut,new StackPane(totalmarks),attendance,bonus, room, books);

        ScrollPane scroller = new ScrollPane(profilevb);
        scroller.setFitToWidth(true);

        BorderPane profiles = new BorderPane(scroller);

        main.window.heightProperty().addListener(e-> scroller.setMinHeight(0.8*main.window.getHeight()));
        profiles.setPadding(new Insets(60));

        return profiles;

    }

}
