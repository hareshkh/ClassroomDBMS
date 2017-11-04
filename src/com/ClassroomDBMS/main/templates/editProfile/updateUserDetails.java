package com.ClassroomDBMS.main.templates.editProfile;

import com.ClassroomDBMS.database.logIn.userLoggedIn;
import com.ClassroomDBMS.database.updateProfile.*;
import com.ClassroomDBMS.main.functions.getMotherboardSN;
import com.ClassroomDBMS.main.functions.profileFaculty;
import com.ClassroomDBMS.main.functions.profileStudent;

import com.ClassroomDBMS.main.windows.home.main;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class updateUserDetails {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern NUMBERS_REGEX =
            Pattern.compile("^[789]\\d{9}$");

    final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

    public BorderPane updateStudentDeatils() {
        String userID = getMotherboardSN.getMotherboardSN();
        String[] currentUserDetail = userLoggedIn.userLoggedIn(userID);

        BorderPane editprofile = new BorderPane();

        BorderPane general = new BorderPane();
        general.setPadding(new Insets(10, 100, 30, 100));

        VBox generalVB = new VBox(15);
        generalVB.setAlignment(Pos.TOP_LEFT);
        generalVB.setPadding(new Insets(0, 0, 10, 0));

        Label generalLabel = new Label("Save details: ");
        generalLabel.setFont(new Font("Cambria", 25));
        generalLabel.setTextFill(Color.web("#5a5a5a"));

        HBox nameLabel = new HBox(50);

        TextField firstName = new TextField(currentUserDetail[2]);
        firstName.setPromptText("First name like " + currentUserDetail[2]);
        firstName.setStyle("-fx-focus-color: transparent;");
        firstName.setPrefHeight(35);
        firstName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                editprofile.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField lastName = new TextField(currentUserDetail[3]);
        lastName.setPromptText("Last name like " + currentUserDetail[3]);
        lastName.setStyle("-fx-focus-color: transparent;");
        lastName.setPrefHeight(35);
        lastName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                editprofile.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        nameLabel.getChildren().addAll(firstName, lastName);

        HBox phoneCollegeLabel = new HBox(50);

        TextField phoneNumber = new TextField(currentUserDetail[6]);
        phoneNumber.setPromptText("Phone Number like " + currentUserDetail[6]);
        phoneNumber.setStyle("-fx-focus-color: transparent;");
        phoneNumber.setPrefHeight(35);

        TextField college = new TextField(currentUserDetail[8]);
        college.setPromptText("College name like " + currentUserDetail[8]);
        college.setStyle("-fx-focus-color: transparent;");
        college.setPrefHeight(35);

        phoneCollegeLabel.getChildren().addAll(phoneNumber, college);

        Label errorIngeneral = new Label();
        errorIngeneral.setTextFill(Color.web("red"));

        Button save = new Button("SAVE");
        save.setFont(new Font("Cambria", 18));
        save.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent;");
        save.setTextFill(Color.web("#fff"));
        save.setCursor(Cursor.HAND);
        save.setOnAction(e -> {
            if (firstName.getText().isEmpty())
                errorIngeneral.setText("Full Name can't be set empty");
            else if (lastName.getText().isEmpty())
                errorIngeneral.setText("Last Name can't be set empty");
            else if (phoneNumber.getText().isEmpty())
                errorIngeneral.setText("Phone Number can't be set empty");
            else if (!validatePhoneNumber(phoneNumber.getText()))
                errorIngeneral.setText("Incorrect mobile number");
            else if (college.getText().isEmpty())
                errorIngeneral.setText("College Name can't be set empty");
            else if (firstName.getText().equals(currentUserDetail[2])
                    && lastName.getText().equals(currentUserDetail[3])
                    && phoneNumber.getText().equals(currentUserDetail[6])
                    && college.getText().equals(currentUserDetail[8]))
                errorIngeneral.setText("No changes made.");
            else {
                String status = generalUpdate.generalUpdateStudent(currentUserDetail[4], firstName.getText(), lastName.getText(), phoneNumber.getText(), college.getText());

                if (status.equals("Success")) {
                    errorIngeneral.setTextFill(Color.web("green"));
                    errorIngeneral.setText("Profile Saved Succcessfully");
                    profileStudent.fullName.setText(firstName.getText() + " " + lastName.getText());
                    profileStudent.phoneNumbercollege.setText(phoneNumber.getText() + ", " + college.getText());
                } else {
                    errorIngeneral.setText(status);
                }
            }
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(ee -> {
                errorIngeneral.setTextFill(Color.web("red"));
                errorIngeneral.setText("");
            });
            new Thread(sleeper).start();
        });

        generalVB.getChildren().addAll(generalLabel, nameLabel, phoneCollegeLabel, errorIngeneral, save);
        general.setTop(generalVB);

        /////////////////////////////////////////////////////////////////////////////////////

        BorderPane password = new BorderPane();
        password.setPadding(new Insets(0, 100, 50, 100));

        VBox passwordVB = new VBox(15);
        passwordVB.setAlignment(Pos.TOP_LEFT);
        passwordVB.setPadding(new Insets(0, 0, 10, 0));

        Label passwordLabel = new Label("Update Password: ");
        passwordLabel.setFont(new Font("Cambria", 25));
        passwordLabel.setTextFill(Color.web("#5a5a5a"));

        HBox oldNewConfirmpasswordLabel = new HBox(50);

        TextField oldPass = new TextField();
        oldPass.setPromptText("Old Password");
        oldPass.setStyle("-fx-focus-color: transparent;");
        oldPass.setPrefHeight(35);

        TextField newPass = new TextField();
        newPass.setPromptText("New Password");
        newPass.setStyle("-fx-focus-color: transparent;");
        newPass.setPrefHeight(35);

        TextField confirmPass = new TextField();
        confirmPass.setPromptText("Confirm Password");
        confirmPass.setStyle("-fx-focus-color: transparent;");
        confirmPass.setPrefHeight(35);

        oldNewConfirmpasswordLabel.getChildren().addAll(oldPass, newPass, confirmPass);

        Label errorInpassword = new Label();
        errorInpassword.setTextFill(Color.web("red"));

        Button update = new Button("UPDATE");
        update.setFont(new Font("Cambria", 18));
        update.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent;");
        update.setTextFill(Color.web("#fff"));
        update.setCursor(Cursor.HAND);
        update.setOnAction(e -> {
            if (oldPass.getText().isEmpty())
                errorInpassword.setText("Old Password can't be empty");
            else if (newPass.getText().isEmpty())
                errorInpassword.setText("New Password can't be empty");
            else if (confirmPass.getText().isEmpty())
                errorInpassword.setText("Confirm Password can't be empty");
            else if (!newPass.getText().equals(confirmPass.getText()))
                errorInpassword.setText("New and confirm password don't match");
            else {
                String status = passwordUpdate.passwordUpdate(currentUserDetail[2], oldPass.getText(), newPass.getText());

                if (status.equals("Success")) {
                    errorInpassword.setTextFill(Color.web("green"));
                    errorInpassword.setText("Password Update successful.");
                } else
                    errorInpassword.setText(status);
            }

            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(ee -> {
                errorInpassword.setTextFill(Color.web("red"));
                errorInpassword.setText("");
            });
            new Thread(sleeper).start();
        });

        passwordVB.getChildren().addAll(passwordLabel, oldNewConfirmpasswordLabel, errorInpassword, update);
        password.setTop(passwordVB);

        firstName.setPrefWidth(0.4 * main.window.getWidth());
        lastName.setPrefWidth(0.4 * main.window.getWidth());
        phoneNumber.setPrefWidth(0.4 * main.window.getWidth());
        college.setPrefWidth(0.4 * main.window.getWidth());
        oldPass.setPrefWidth(0.3 * main.window.getWidth());
        newPass.setPrefWidth(0.3 * main.window.getWidth());
        confirmPass.setPrefWidth(0.3 * main.window.getWidth());

        main.window.widthProperty().addListener(e -> {
            firstName.setPrefWidth(0.4 * main.window.getWidth());
            lastName.setPrefWidth(0.4 * main.window.getWidth());
            phoneNumber.setPrefWidth(0.4 * main.window.getWidth());
            college.setPrefWidth(0.4 * main.window.getWidth());
            oldPass.setPrefWidth(0.3 * main.window.getWidth());
            newPass.setPrefWidth(0.3 * main.window.getWidth());
            confirmPass.setPrefWidth(0.3 * main.window.getWidth());
        });

        editprofile.setTop(general);
        editprofile.setCenter(password);

        return editprofile;
    }

    public BorderPane updateFacultyDetails() {
        String userID = getMotherboardSN.getMotherboardSN();
        String[] currentUserDetail = userLoggedIn.userLoggedIn(userID);

        BorderPane editprofile = new BorderPane();

        BorderPane general = new BorderPane();
        general.setPadding(new Insets(10, 100, 30, 100));

        VBox generalVB = new VBox(15);
        generalVB.setAlignment(Pos.TOP_LEFT);
        generalVB.setPadding(new Insets(0, 0, 10, 0));

        Label generalLabel = new Label("Save details: ");
        generalLabel.setFont(new Font("Cambria", 25));
        generalLabel.setTextFill(Color.web("#5a5a5a"));

        HBox nameLabel = new HBox(50);

        TextField firstName = new TextField(currentUserDetail[2]);
        firstName.setPromptText("First name like " + currentUserDetail[2]);
        firstName.setStyle("-fx-focus-color: transparent;");
        firstName.setPrefHeight(35);
        firstName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                editprofile.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField lastName = new TextField(currentUserDetail[3]);
        lastName.setPromptText("Last name like " + currentUserDetail[3]);
        lastName.setStyle("-fx-focus-color: transparent;");
        lastName.setPrefHeight(35);
        lastName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                editprofile.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        nameLabel.getChildren().addAll(firstName, lastName);

        HBox designatioPhoneLabel = new HBox(50);

        TextField designation = new TextField(currentUserDetail[5]);
        designation.setPromptText("Designation name like " + currentUserDetail[5]);
        designation.setStyle("-fx-focus-color: transparent;");
        designation.setPrefHeight(35);

        TextField phoneNumber = new TextField(currentUserDetail[6]);
        phoneNumber.setPromptText("Phone Number like " + currentUserDetail[6]);
        phoneNumber.setStyle("-fx-focus-color: transparent;");
        phoneNumber.setPrefHeight(35);

        designatioPhoneLabel.getChildren().addAll(designation, phoneNumber);

        Label errorIngeneral = new Label();
        errorIngeneral.setTextFill(Color.web("red"));

        Button save = new Button("SAVE");
        save.setFont(new Font("Cambria", 18));
        save.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent;");
        save.setTextFill(Color.web("#fff"));
        save.setCursor(Cursor.HAND);
        save.setOnAction(e -> {
            if (firstName.getText().isEmpty())
                errorIngeneral.setText("First Name can't be set empty");
            else if (lastName.getText().isEmpty())
                errorIngeneral.setText("Last Name can't be set empty");
            else if (phoneNumber.getText().isEmpty())
                errorIngeneral.setText("Phone Number can't be set empty");
            else if (!validatePhoneNumber(phoneNumber.getText()))
                errorIngeneral.setText("Incorrect mobile number");
            else if (designation.getText().isEmpty())
                errorIngeneral.setText("Designation can't be set empty");
            else if (firstName.getText().equals(currentUserDetail[2])
                    && lastName.getText().equals(currentUserDetail[3])
                    && phoneNumber.getText().equals(currentUserDetail[6])
                    && designation.getText().equals(currentUserDetail[5]))
                errorIngeneral.setText("No changes made.");
            else {
                String status = generalUpdate.generalUpdateFaculty(currentUserDetail[4], firstName.getText(), lastName.getText(), designation.getText(), phoneNumber.getText());

                if (status.equals("Success")) {
                    errorIngeneral.setTextFill(Color.web("green"));
                    errorIngeneral.setText("Profile Saved Succcessfully");
                    profileFaculty.fullName.setText(firstName.getText() + " " + lastName.getText());
                    profileFaculty.phoneNumberDesignation.setText(phoneNumber.getText() + ", " + designation.getText());
                } else {
                    errorIngeneral.setText(status);
                }
            }
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(ee -> {
                errorIngeneral.setTextFill(Color.web("red"));
                errorIngeneral.setText("");
            });
            new Thread(sleeper).start();
        });

        generalVB.getChildren().addAll(generalLabel, nameLabel, designatioPhoneLabel, errorIngeneral, save);
        general.setTop(generalVB);

        /////////////////////////////////////////////////////////////////////////////////////

        BorderPane password = new BorderPane();
        password.setPadding(new Insets(0, 100, 50, 100));

        VBox passwordVB = new VBox(15);
        passwordVB.setAlignment(Pos.TOP_LEFT);
        passwordVB.setPadding(new Insets(0, 0, 10, 0));

        Label passwordLabel = new Label("Update Password: ");
        passwordLabel.setFont(new Font("Cambria", 25));
        passwordLabel.setTextFill(Color.web("#5a5a5a"));

        HBox oldNewConfirmpasswordLabel = new HBox(50);

        TextField oldPass = new TextField();
        oldPass.setPromptText("Old Password");
        oldPass.setStyle("-fx-focus-color: transparent;");
        oldPass.setPrefHeight(35);

        TextField newPass = new TextField();
        newPass.setPromptText("New Password");
        newPass.setStyle("-fx-focus-color: transparent;");
        newPass.setPrefHeight(35);

        TextField confirmPass = new TextField();
        confirmPass.setPromptText("Confirm Password");
        confirmPass.setStyle("-fx-focus-color: transparent;");
        confirmPass.setPrefHeight(35);

        oldNewConfirmpasswordLabel.getChildren().addAll(oldPass, newPass, confirmPass);

        Label errorInpassword = new Label();
        errorInpassword.setTextFill(Color.web("red"));

        Button update = new Button("UPDATE");
        update.setFont(new Font("Cambria", 18));
        update.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent;");
        update.setTextFill(Color.web("#fff"));
        update.setCursor(Cursor.HAND);
        update.setOnAction(e -> {
            if (oldPass.getText().isEmpty())
                errorInpassword.setText("Old Password can't be empty");
            else if (newPass.getText().isEmpty())
                errorInpassword.setText("New Password can't be empty");
            else if (confirmPass.getText().isEmpty())
                errorInpassword.setText("Confirm Password can't be empty");
            else if (!newPass.getText().equals(confirmPass.getText()))
                errorInpassword.setText("New and confirm password don't match");
            else {
                String status = passwordUpdate.passwordUpdate(currentUserDetail[2], oldPass.getText(), newPass.getText());

                if (status.equals("Success")) {
                    errorInpassword.setTextFill(Color.web("green"));
                    errorInpassword.setText("Password Update successful.");
                } else
                    errorInpassword.setText(status);
            }

            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(ee -> {
                errorInpassword.setTextFill(Color.web("red"));
                errorInpassword.setText("");
            });
            new Thread(sleeper).start();
        });

        passwordVB.getChildren().addAll(passwordLabel, oldNewConfirmpasswordLabel, errorInpassword, update);
        password.setTop(passwordVB);

        firstName.setPrefWidth(0.4 * main.window.getWidth());
        lastName.setPrefWidth(0.4 * main.window.getWidth());
        phoneNumber.setPrefWidth(0.4 * main.window.getWidth());
        designation.setPrefWidth(0.4 * main.window.getWidth());
        oldPass.setPrefWidth(0.3 * main.window.getWidth());
        newPass.setPrefWidth(0.3 * main.window.getWidth());
        confirmPass.setPrefWidth(0.3 * main.window.getWidth());

        main.window.widthProperty().addListener(e -> {
            firstName.setPrefWidth(0.4 * main.window.getWidth());
            lastName.setPrefWidth(0.4 * main.window.getWidth());
            phoneNumber.setPrefWidth(0.4 * main.window.getWidth());
            designation.setPrefWidth(0.4 * main.window.getWidth());
            oldPass.setPrefWidth(0.3 * main.window.getWidth());
            newPass.setPrefWidth(0.3 * main.window.getWidth());
            confirmPass.setPrefWidth(0.3 * main.window.getWidth());
        });

        editprofile.setTop(general);
        editprofile.setCenter(password);

        return editprofile;
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
