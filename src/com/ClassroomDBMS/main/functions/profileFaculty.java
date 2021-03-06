package com.ClassroomDBMS.main.functions;

import com.ClassroomDBMS.database.classrooms.DBClassrooms;
import com.ClassroomDBMS.database.signIn.deleteAccount;
import com.ClassroomDBMS.database.signIn.userSignOut;
import com.ClassroomDBMS.main.models.ClassroomModel;
import com.ClassroomDBMS.main.templates.AddCourse.AddCourseTemplate;
import com.ClassroomDBMS.main.templates.editProfile.updateUserDetails;
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

public class profileFaculty {

    public static Label fullName;
    public static Label emailID;
    public static Label phoneNumberDesignation;
    public static Label course;
    public static Label addCourse;
    public static Label speakOut;

    public static StackPane addCoursePane, speakOutPane, logoutPane;

    public static String facultyEmailId;

    public static VBox buttons;

    public static BorderPane userOptions;

    public static BorderPane options;
    public static BorderPane optionData;
    public static BorderPane optionDetails;

    public static Scene main(String[] profileDetails) {

        facultyEmailId = profileDetails[4];

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

        phoneNumberDesignation = new Label(profileDetails[6] + ",  " + profileDetails[5]);
        phoneNumberDesignation.setFont(new Font("Cambria", 15));
        phoneNumberDesignation.setTextFill(Color.web("#ededed"));
        phoneNumberDesignation.setWrapText(true);

        HBox profileSetting = new HBox(10);
        profileSetting.setAlignment(Pos.TOP_CENTER);

        Button editButton = GlyphsDude.createIconButton(FontAwesomeIcon.EDIT, "Edit Profile");
        editButton.setCursor(Cursor.HAND);

        Button deleteButton = GlyphsDude.createIconButton(FontAwesomeIcon.CUT, "Delete Profile");
        deleteButton.setCursor(Cursor.HAND);

        profileSetting.getChildren().addAll(editButton, deleteButton);

        userData.getChildren().addAll(logo, fullName, emailID, phoneNumberDesignation, profileSetting);
        userData.setStyle("-fx-border-color: #fff;-fx-border-width: 0 0 2 0;-fx-underline: true;");

        options.setTop(userData);

        optionData = new BorderPane();

        buttons = new VBox(15);

        ArrayList<ClassroomModel> classrooms = DBClassrooms.getAllClassroomsByEmail(profileDetails[4]);

        for (ClassroomModel model : classrooms) {
            course = GlyphsDude.createIconLabel(FontAwesomeIcon.BOOK,
                    model.getCourseId(),
                    "20",
                    "18",
                    ContentDisplay.LEFT);
            course.setFont(new Font("Cambria", 20));
            course.setTextFill(Color.web("#171717"));
            course.setPadding(new Insets(10));
            StackPane coursePane = new StackPane(course);
            coursePane.setAlignment(Pos.BASELINE_LEFT);
            coursePane.setStyle("-fx-background-color: grey");
            coursePane.setCursor(Cursor.HAND);

            buttons.getChildren().addAll(coursePane);

            coursePane.setOnMouseClicked(e -> {
                optionData.setTop(model.getInfo());
            });
        }

        { // ADD COURSE
            addCourse = GlyphsDude.createIconLabel(FontAwesomeIcon.SEARCH,
                    "  Add Course",
                    "20",
                    "18",
                    ContentDisplay.LEFT);
            addCourse.setFont(new Font("Cambria", 20));
            addCourse.setTextFill(Color.web("#171717"));
            addCourse.setPadding(new Insets(10));
            addCoursePane = new StackPane(addCourse);
            addCoursePane.setAlignment(Pos.BASELINE_LEFT);
            addCoursePane.setStyle("-fx-background-color: grey");
            addCoursePane.setCursor(Cursor.HAND);

            addCoursePane.setOnMouseClicked(e -> {
                optionData.setTop(AddCourseTemplate.getAddCourseView(profileDetails[4]));
                toggleTextColors("red", "#171717", "#171717", "#171717");
            });
        }

        { // SPEAK OUT
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

            speakOutPane.setOnMouseClicked(e -> {
                optionData.setTop(notices.notices(emailID.getText()));
                toggleTextColors("#171717", "#171717", "red", "#171717");
                notices.messages.requestFocus();
            });
        }

        buttons.getChildren().addAll(addCoursePane, speakOutPane);
        options.setCenter(buttons);

        { // LOGOUT
            Label logout = GlyphsDude.createIconLabel(FontAwesomeIcon.SIGN_OUT,
                    "  Log Out",
                    "20",
                    "18",
                    ContentDisplay.LEFT);
            logout.setFont(new Font("Cambria", 20));
            logout.setTextFill(Color.web("#171717"));
            logout.setPadding(new Insets(10));
            logoutPane = new StackPane(logout);
            logoutPane.setAlignment(Pos.BASELINE_LEFT);
            logoutPane.setStyle("-fx-background-color: grey");
            logoutPane.setCursor(Cursor.HAND);

            logoutPane.setOnMouseClicked(e -> {
                userSignOut.userSignOut();
                main.window.setScene(loginHome.homeView());
            });
        }

        options.setBottom(logoutPane);
        optionDetails.setCenter(options);

        editButton.setOnAction(e -> {
            e.consume();
            updateUserDetails ob = new updateUserDetails();
            optionData.setTop(ob.updateFacultyDetails());
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
                    deleteAccount.deleteFacultyAccount(emailID.getText());
                main.window.setScene(loginHome.homeView());
            } catch (Exception exe) {
                exe.getMessage();
            }
        });

        userOptions.setLeft(optionDetails);
        userOptions.setCenter(optionData);

        Scene scene = new Scene(userOptions, 800, 500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        String image = profileFaculty.class.getResource("../resources/images/splash.jpg").toExternalForm();
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
    }

    public static void updateCourses() {
        ArrayList<ClassroomModel> classrooms = DBClassrooms.getAllClassroomsByEmail(facultyEmailId);
        buttons.getChildren().clear();
        for (ClassroomModel model : classrooms) {
            course = GlyphsDude.createIconLabel(FontAwesomeIcon.BOOK,
                    model.getCourseId(),
                    "20",
                    "18",
                    ContentDisplay.LEFT);
            course.setFont(new Font("Cambria", 20));
            course.setTextFill(Color.web("#171717"));
            course.setPadding(new Insets(10));
            StackPane coursePane = new StackPane(course);
            coursePane.setAlignment(Pos.BASELINE_LEFT);
            coursePane.setStyle("-fx-background-color: grey");
            coursePane.setCursor(Cursor.HAND);

            buttons.getChildren().add(0, coursePane);

            coursePane.setOnMouseClicked(e -> {
                optionData.setTop(model.getInfo());
            });
        }

        buttons.getChildren().addAll(addCoursePane, speakOutPane);
        options.setCenter(buttons);

    }

}
