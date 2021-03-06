package com.ClassroomDBMS.main.windows.login;

import com.ClassroomDBMS.database.logIn.dbLoginCheck;
import com.ClassroomDBMS.main.functions.profileFaculty;
import com.ClassroomDBMS.main.functions.profileStudent;
import com.ClassroomDBMS.main.windows.home.main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class userLogin {

    public String[] status;

    public void userLogin() {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        Stage loginStage = new Stage();
        loginStage.setTitle("Classroom DBMS login");
        loginStage.initModality(Modality.APPLICATION_MODAL);

        BorderPane loginPane = new BorderPane();
        loginPane.setPadding(new Insets(50, 20, 20, 20));

        HBox loginHeader = new HBox();

        loginHeader.setAlignment(Pos.BASELINE_CENTER);
        Label header = new Label("Log In");
        header.setFont(new Font("Cambria", 25));
        header.setTextFill(Color.web("#fff"));
        loginHeader.getChildren().add(header);

        loginPane.setTop(loginHeader);

        VBox vb = new VBox(15);
        vb.setPadding(new Insets(30, 20, -20, 20));

        TextField emailId = new TextField();
        emailId.setPromptText("email address");
        emailId.setStyle("-fx-border-radius: 100");
        emailId.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                loginPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setStyle("-fx-border-radius: 100");

        Label error = new Label();
        error.setTextFill(Color.web("red"));

        vb.getChildren().addAll(emailId, password, error);

        loginPane.setCenter(vb);

        HBox loginRow = new HBox();
        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("Cambria", 18));
        loginButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        loginButton.setTextFill(Color.web("#fff"));
        loginRow.getChildren().addAll(loginButton);
        loginRow.setAlignment(Pos.BASELINE_CENTER);

        loginButton.setOnAction(e -> {
            e.consume();
            if (emailId.getText().isEmpty())
                error.setText("EmailId can't be empty");
            else if (password.getText().isEmpty())
                error.setText("Password can't be empty");
            else {
                status = dbLoginCheck.dbLoginCheck(emailId.getText(), password.getText());
                if (status[0].equals("success")) {
                    if(status[1].equals("student")) {
                        main.window.setScene(profileStudent.main(status));
                    } else if (status[1].equals("faculty")){
                        main.window.setScene(profileFaculty.main(status));
                    }
                    loginStage.close();
                } else
                    error.setText("Incorrect Username / Email Id or password !");
            }
        });

        loginPane.setBottom(loginRow);

        Scene loginScene = new Scene(loginPane, 400, 280);
        loginScene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER),
                loginButton::fire
        );

        loginScene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());
        loginStage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/ClassroomDBMS.png")));

        loginStage.setScene(loginScene);
        loginStage.setResizable(false);
        loginStage.show();

    }
}
