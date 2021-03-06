package com.ClassroomDBMS.main.functions;

import com.ClassroomDBMS.database.signIn.deleteAccount;
import com.ClassroomDBMS.database.signIn.userSignOut;
import com.ClassroomDBMS.database.studentclassrooms.DBStudentClassrooms;
import com.ClassroomDBMS.main.models.StudentCourseModel;
import com.ClassroomDBMS.main.templates.Home.courseInfo;
import com.ClassroomDBMS.main.templates.SelectCourses.SelectCoursesTemplate;
import com.ClassroomDBMS.main.templates.StudentCourse.StudentCourseTemplate;
import com.ClassroomDBMS.main.templates.editProfile.updateUserDetails;
import com.ClassroomDBMS.main.templates.search.peopleSearch;
import com.ClassroomDBMS.main.templates.speakouts.notices;
import com.ClassroomDBMS.main.windows.home.main;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Optional;

public class profileStudent {

    public static Label fullName;
    public static Label emailID;
    public static Label phoneNumbercollege;
    public static Label course;
    public static Label selectCourse;
    public static Label findPeople;
    public static Label speakOut;

    public static VBox buttons;

    public static BorderPane optionDetails;
    public static BorderPane optionData;
    public static BorderPane userOptions;
    public static BorderPane options;

    public static StackPane coursePane, selectCoursePane, findPeoplePane, speakOutPane;

    public static String studentEmailId;

    public static Scene main(String[] profileDetails) {

        studentEmailId = profileDetails[4];

        userOptions = new BorderPane();

        optionDetails = new BorderPane();
        optionDetails.setStyle("-fx-background-color: #171717");
        optionDetails.setPrefWidth(220);

        options = new BorderPane();
        VBox userData = new VBox(10);
        userData.setPadding(new Insets(0, 0, 20, 0));
        userData.setAlignment(Pos.TOP_CENTER);

        Label userLOGO = GlyphsDude.createIconLabel(FontAwesomeIcon.USER_SECRET,
                "",
                "100",
                "0",
                ContentDisplay.LEFT);
        userLOGO.setTextFill(Color.web("grey"));
        userLOGO.setPadding(new Insets(15, 0, 0, 10));
        StackPane logo = new StackPane(userLOGO);
        logo.setStyle("-fx-background-color: #fff");

        fullName = new Label(profileDetails[2] + " " + profileDetails[3]);
        fullName.setFont(new Font("Cambria", 25));
        fullName.setTextFill(Color.web("#ededed"));

        emailID = new Label(profileDetails[4]);
        emailID.setFont(new Font("Cambria", 15));
        emailID.setTextFill(Color.web("#ededed"));

        phoneNumbercollege = new Label(profileDetails[6] + ",  " + profileDetails[8]);
        phoneNumbercollege.setFont(new Font("Cambria", 15));
        phoneNumbercollege.setTextFill(Color.web("#ededed"));
        phoneNumbercollege.setWrapText(true);

        HBox profileSetting = new HBox(10);
        profileSetting.setAlignment(Pos.TOP_CENTER);

        Button editButton = GlyphsDude.createIconButton(FontAwesomeIcon.EDIT, "Edit Profile");
        editButton.setCursor(Cursor.HAND);

        Button deleteButton = GlyphsDude.createIconButton(FontAwesomeIcon.CUT, "Delete Profile");
        deleteButton.setCursor(Cursor.HAND);

        profileSetting.getChildren().addAll(editButton, deleteButton);

        userData.getChildren().addAll(logo, fullName, emailID, phoneNumbercollege, profileSetting);
        userData.setStyle("-fx-border-color: #fff;-fx-border-width: 0 0 2 0;-fx-underline: true;");

        options.setTop(userData);

        buttons = new VBox(15);

        ArrayList<StudentCourseModel> studentCourseModels = DBStudentClassrooms.getAllCoursesByEmailId(studentEmailId);

        for (StudentCourseModel model : studentCourseModels) {
            course = GlyphsDude.createIconLabel(FontAwesomeIcon.BOOK,
                    model.getCourseId(),
                    "20",
                    "18",
                    ContentDisplay.LEFT);
            course.setFont(new Font("Cambria", 20));
            course.setTextFill(Color.web("#171717"));
            course.setPadding(new Insets(10));
            coursePane = new StackPane(course);
            coursePane.setAlignment(Pos.BASELINE_LEFT);
            coursePane.setStyle("-fx-background-color: grey");
            coursePane.setCursor(Cursor.HAND);

            buttons.getChildren().add(coursePane);

            coursePane.setOnMouseClicked(e -> {
                optionData.setTop(StudentCourseTemplate.getStudentCourseDetailsView(model.getStudentEmailId(), model.getCourseId()));
                toggleTextColors("red", "#171717", "#171717", "#171717");
            });

        }

        selectCourse = GlyphsDude.createIconLabel(FontAwesomeIcon.USERS,
                " Select Courses",
                "20",
                "18",
                ContentDisplay.LEFT);
        selectCourse.setFont(new Font("Cambria", 20));
        selectCourse.setTextFill(Color.web("#171717"));
        selectCourse.setPadding(new Insets(10));
        selectCoursePane = new StackPane(selectCourse);
        selectCoursePane.setAlignment(Pos.BASELINE_LEFT);
        selectCoursePane.setStyle("-fx-background-color: grey");
        selectCoursePane.setCursor(Cursor.HAND);
        selectCoursePane.setOnMouseClicked(e -> {
            optionData.setTop(SelectCoursesTemplate.getSelectCourseView(studentEmailId));
        });

        findPeople = GlyphsDude.createIconLabel(FontAwesomeIcon.SEARCH,
                "  Find Students",
                "20",
                "18",
                ContentDisplay.LEFT);
        findPeople.setFont(new Font("Cambria", 20));
        findPeople.setTextFill(Color.web("#171717"));
        findPeople.setPadding(new Insets(10));
        findPeoplePane = new StackPane(findPeople);
        findPeoplePane.setAlignment(Pos.BASELINE_LEFT);
        findPeoplePane.setStyle("-fx-background-color: grey");
        findPeoplePane.setCursor(Cursor.HAND);

        speakOut = GlyphsDude.createIconLabel(FontAwesomeIcon.WECHAT,
                "  SpeakOut",
                "20",
                "18",
                ContentDisplay.LEFT);
        speakOut.setFont(new Font("Cambria", 20));
        speakOut.setTextFill(Color.web("#171717"));
        speakOut.setPadding(new Insets(10));
        speakOutPane = new StackPane(speakOut);
        speakOutPane.setAlignment(Pos.BASELINE_LEFT);
        speakOutPane.setStyle("-fx-background-color: grey");
        speakOutPane.setCursor(Cursor.HAND);

        buttons.getChildren().addAll(selectCoursePane, findPeoplePane, speakOutPane);
        options.setCenter(buttons);

        Label logout = GlyphsDude.createIconLabel(FontAwesomeIcon.SIGN_OUT,
                "  Log Out",
                "20",
                "18",
                ContentDisplay.LEFT);
        logout.setFont(new Font("Cambria", 20));
        logout.setTextFill(Color.web("#171717"));
        logout.setPadding(new Insets(10));
        StackPane logoutPane = new StackPane(logout);
        logoutPane.setAlignment(Pos.BASELINE_LEFT);
        logoutPane.setStyle("-fx-background-color: grey");
        logoutPane.setCursor(Cursor.HAND);

        options.setBottom(logoutPane);
        optionDetails.setCenter(options);

        optionData = new BorderPane();

        editButton.setOnAction(e -> {
            e.consume();
            updateUserDetails ob = new updateUserDetails();
            optionData.setTop(ob.updateStudentDeatils());
            toggleTextColors("#171717", "#171717", "#171717", "#171717");
        });

        deleteButton.setOnAction(e -> {
            toggleTextColors("#171717", "#171717", "#171717", "#171717");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Profile Delete Confirmation");
            alert.setHeaderText("All your user details will be lost. Still your announcements could be seen");
            alert.setContentText("Are you ok with this?");

            try {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                    deleteAccount.deleteStudentAccount(emailID.getText());
                main.window.setScene(loginHome.homeView());
            } catch (Exception exe) {
                exe.getMessage();
            }
        });

        peopleSearch ob = new peopleSearch();
        findPeoplePane.setOnMouseClicked(e -> {
            optionData.setTop(ob.peoplesearch());
            toggleTextColors("#171717", "red", "#171717", "#171717");
        });

        speakOutPane.setOnMouseClicked(e -> {
            optionData.setTop(notices.notices(emailID.getText()));
            toggleTextColors("#171717", "#171717", "red", "#171717");
            notices.messages.requestFocus();
        });

        logoutPane.setOnMouseClicked(e -> {
            userSignOut.userSignOut();
            main.window.setScene(loginHome.homeView());
        });

        userOptions.setLeft(optionDetails);
        userOptions.setCenter(optionData);

        Scene scene = new Scene(userOptions, 800, 500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        String image = profileStudent.class.getResource("../resources/images/splash.jpg").toExternalForm();
        optionData.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        optionDetails.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        return scene;
    }

    public static void toggleTextColors(String courseColor, String findColor, String speakoutColor, String submissionColor) {
//        course.setTextFill(Color.web(courseColor));
//        findPeople.setTextFill(Color.web(findColor));
//        speakOut.setTextFill(Color.web(speakoutColor));
//        submission.setTextFill(Color.web(submissionColor));
    }

    public static void updateCourses() {
        ArrayList<StudentCourseModel> studentCourseModels = DBStudentClassrooms.getAllCoursesByEmailId(studentEmailId);
        buttons.getChildren().clear();

        for (StudentCourseModel model : studentCourseModels) {
            course = GlyphsDude.createIconLabel(FontAwesomeIcon.BOOK,
                    model.getCourseId(),
                    "20",
                    "18",
                    ContentDisplay.LEFT);
            course.setFont(new Font("Cambria", 20));
            course.setTextFill(Color.web("#171717"));
            course.setPadding(new Insets(10));
            coursePane = new StackPane(course);
            coursePane.setAlignment(Pos.BASELINE_LEFT);
            coursePane.setStyle("-fx-background-color: grey");
            coursePane.setCursor(Cursor.HAND);

            buttons.getChildren().add(coursePane);

            coursePane.setOnMouseClicked(e -> {
                optionData.setTop(StudentCourseTemplate.getStudentCourseDetailsView(model.getStudentEmailId(), model.getCourseId()));
                toggleTextColors("red", "#171717", "#171717", "#171717");
            });

        }

        buttons.getChildren().addAll(selectCoursePane, findPeoplePane, speakOutPane);

    }

}
