package com.ClassroomDBMS.main.windows.signUp;

import com.ClassroomDBMS.database.signIn.dbSignUp;
import com.ClassroomDBMS.main.functions.profileFaculty;
import com.ClassroomDBMS.main.windows.home.main;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class facultySignUp {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern NUMBERS_REGEX =
            Pattern.compile("^[789]\\d{9}$");

    public String[] status;

    public void signUp() {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        Stage signUpStage = new Stage();
        signUpStage.setTitle("Classroom DBMS Signup");
        signUpStage.initModality(Modality.APPLICATION_MODAL);

        BorderPane signUpPane = new BorderPane();
        signUpPane.setPadding(new Insets(30, 20, 20, 20));

        HBox signUpHeader = new HBox();

        signUpHeader.setAlignment(Pos.BASELINE_CENTER);
        Label header = new Label("Sign Up");
        header.setFont(new Font("Cambria", 25));
        header.setTextFill(Color.web("#fff"));
        signUpHeader.getChildren().add(header);

        signUpPane.setTop(signUpHeader);

        VBox vb = new VBox(15);
        vb.setPadding(new Insets(30, 20, -20, 20));

        TextField firstName = new TextField();
        firstName.setPromptText("First Name");
        firstName.setStyle("-fx-border-radius: 100");
        firstName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                signUpPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");
        lastName.setStyle("-fx-border-radius: 100");
        lastName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                signUpPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField email = new TextField();
        email.setPromptText("Email Id");
        email.setStyle("-fx-border-radius: 100");

        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setStyle("-fx-border-radius: 100");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm password");
        confirmPassword.setStyle("-fx-border-radius: 100");

        TextField designation = new TextField();
        designation.setPromptText("Designation");
        designation.setStyle("-fx-border-radius: 100");

        TextField phoneNumber = new TextField();
        phoneNumber.setPromptText("Phone Number");
        phoneNumber.setStyle("-fx-border-radius: 100");

        Label error = new Label();
        error.setTextFill(Color.web("red"));

        vb.getChildren().addAll(firstName, lastName, email, password, confirmPassword, designation, phoneNumber, error);

        signUpPane.setCenter(vb);

        HBox signUpRow = new HBox();
        Button signUpButton = new Button("SignUp");
        signUpButton.setFont(new Font("Cambria", 18));
        signUpButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        signUpButton.setTextFill(Color.web("#fff"));
        signUpRow.getChildren().addAll(signUpButton);
        signUpRow.setAlignment(Pos.BASELINE_CENTER);

        signUpButton.setOnAction(e -> {
            if (firstName.getText().isEmpty())
                error.setText("First Name can't be empty");
            else if (lastName.getText().isEmpty())
                error.setText("Last Name can't be empty");
            else if (email.getText().isEmpty())
                error.setText("Email Id can't be empty");
            else if (!validate(email.getText()))
                error.setText("Email ID incorrect");
            else if (password.getText().isEmpty())
                error.setText("Password can't be empty");
            else if (confirmPassword.getText().isEmpty())
                error.setText("ConfirmPassword can't be empty");
            else if (!password.getText().equals(confirmPassword.getText()))
                error.setText("Password and confirm password don't match");
            else if (phoneNumber.getText().isEmpty())
                error.setText("Phone Number can't be empty");
            else if (designation.getText().isEmpty())
                error.setText("designation can't be empty");
            else if (!validatePhoneNumber(phoneNumber.getText()))
                error.setText("Phone Number incorrect");
            else {
                status = dbSignUp.facultySignUp(firstName.getText(), lastName.getText(), email.getText(), password.getText(), designation.getText(), phoneNumber.getText());
                if (status[0].equals("success")) {
                    main.window.setScene(profileFaculty.main(status));
                    signUpStage.close();
                } else {
                    error.setText(status[0]);
                    System.out.println(status[0]);
                }
            }
        });

        signUpPane.setBottom(signUpRow);

        Scene SignUpScene = new Scene(signUpPane, 400, 480);
        SignUpScene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER),
                () -> signUpButton.fire()
        );

        SignUpScene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());
        signUpStage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/ClassroomDBMS.png")));

        signUpStage.setScene(SignUpScene);
        signUpStage.setResizable(false);
        signUpStage.showAndWait();

    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePhoneNumber(String phStr) {
        Matcher matcher = NUMBERS_REGEX.matcher(phStr);
        return matcher.find();
    }
}
