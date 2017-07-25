package com.ClassroomDBMS.main.functions;

import com.ClassroomDBMS.main.windows.home.main;
import com.ClassroomDBMS.main.templates.search.peopleSearch;
import com.ClassroomDBMS.main.templates.editProfile.*;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class profile {

    public static Scene main(String[] profileDetails){
        BorderPane userOptions = new BorderPane();

        BorderPane optionDetails = new BorderPane();
        optionDetails.setStyle("-fx-background-color: #171717");
        optionDetails.setPrefWidth(220);

        BorderPane options = new BorderPane();
        VBox userData = new VBox(10);
        userData.setPadding(new Insets(0,0,20,0));
        userData.setAlignment(Pos.TOP_CENTER);

        Label userLOGO = GlyphsDude.createIconLabel( FontAwesomeIcon.USER_SECRET,
                "",
                "100",
                "0",
                ContentDisplay.LEFT );
        userLOGO.setTextFill(Color.web("grey"));
        userLOGO.setPadding(new Insets(15,0,0,10));
        StackPane logo = new StackPane(userLOGO);
        logo.setStyle("-fx-background-color: #fff");

        Label fullName = new Label(profileDetails[1]);
        fullName.setFont(new Font("Cambria", 25));
        fullName.setTextFill(Color.web("#ededed"));

        Label emailID = new Label(profileDetails[2]);
        emailID.setFont(new Font("Cambria", 15));
        emailID.setTextFill(Color.web("#ededed"));

        Label college = new Label(profileDetails[3]+",  "+profileDetails[5]);
        college.setFont(new Font("Cambria", 15));
        college.setTextFill(Color.web("#ededed"));
        college.setWrapText(true);

        Button editButton = GlyphsDude.createIconButton(FontAwesomeIcon.EDIT,"Edit Profile");
        editButton.setStyle("-fx-border-radius: 100");

        userData.getChildren().addAll(logo,fullName,emailID,college,editButton);
        userData.setStyle("-fx-border-color: #fff;-fx-border-width: 0 0 2 0;-fx-underline: true;");

        options.setTop(userData);

        VBox buttons = new VBox(15);

        Label findPeople = GlyphsDude.createIconLabel( FontAwesomeIcon.SEARCH,
                "  Find Students",
                "20",
                "18",
                ContentDisplay.LEFT );
        findPeople.setFont(new Font("Cambria", 20));
        findPeople.setTextFill(Color.web("#171717"));
        findPeople.setPadding(new Insets(10));
        StackPane findPeoplePane = new StackPane(findPeople);
        findPeoplePane.setAlignment(Pos.BASELINE_LEFT);
        findPeoplePane.setStyle("-fx-background-color: grey");

        Label speakOut = GlyphsDude.createIconLabel( FontAwesomeIcon.WECHAT,
                "  SpeakOut",
                "20",
                "18",
                ContentDisplay.LEFT );
        speakOut.setFont(new Font("Cambria", 20));
        speakOut.setTextFill(Color.web("#171717"));
        speakOut.setPadding(new Insets(10));
        StackPane speakOutPane = new StackPane(speakOut);
        speakOutPane.setAlignment(Pos.BASELINE_LEFT);
        speakOutPane.setStyle("-fx-background-color: grey");

        Label TAs = GlyphsDude.createIconLabel( FontAwesomeIcon.USERS,
                "  Teaching Assistant",
                "20",
                "18",
                ContentDisplay.LEFT );
        TAs.setFont(new Font("Cambria", 20));
        TAs.setTextFill(Color.web("#171717"));
        TAs.setPadding(new Insets(10));
        StackPane TAsPane = new StackPane(TAs);
        TAsPane.setAlignment(Pos.BASELINE_LEFT);
        TAsPane.setStyle("-fx-background-color: grey");

        buttons.getChildren().addAll(findPeoplePane, speakOutPane, TAsPane);
        options.setCenter(buttons);

        Label logout = GlyphsDude.createIconLabel( FontAwesomeIcon.SIGN_OUT,
                "  Log Out",
                "20",
                "18",
                ContentDisplay.LEFT );
        logout.setFont(new Font("Cambria", 20));
        logout.setTextFill(Color.web("#171717"));
        logout.setPadding(new Insets(10));
        StackPane logoutPane = new StackPane(logout);
        logoutPane.setAlignment(Pos.BASELINE_LEFT);
        logoutPane.setStyle("-fx-background-color: grey");

        options.setBottom(logoutPane);
        optionDetails.setCenter(options);

        BorderPane optionData = new BorderPane();

        editButton.setOnAction(e-> {
            updateUserDetails ob = new updateUserDetails();
            optionData.setTop(ob.updateUserDetails());

            findPeople.setTextFill(Color.web("#171717"));
            speakOut.setTextFill(Color.web("#171717"));
            TAs.setTextFill(Color.web("#171717"));
        });

        findPeople.setOnMouseClicked(e-> {
            peopleSearch ob = new peopleSearch();
            optionData.setTop(ob.peoplesearch());

            findPeople.setTextFill(Color.web("red"));
            speakOut.setTextFill(Color.web("#171717"));
            TAs.setTextFill(Color.web("#171717"));
        });

        speakOut.setOnMouseClicked(e-> {
//            peopleSearch ob = new peopleSearch();
//            optionData.setTop(ob.peoplesearch());

            findPeople.setTextFill(Color.web("#171717"));
            speakOut.setTextFill(Color.web("red"));
            TAs.setTextFill(Color.web("#171717"));
        });

        TAs.setOnMouseClicked(e-> {
//            peopleSearch ob = new peopleSearch();
//            optionData.setTop(ob.peoplesearch());

            findPeople.setTextFill(Color.web("#171717"));
            speakOut.setTextFill(Color.web("#171717"));
            TAs.setTextFill(Color.web("red"));
        });

        userOptions.setLeft(optionDetails);
        userOptions.setCenter(optionData);

        Scene scene = new Scene(userOptions,800,500);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());
        return scene;
    }
}
