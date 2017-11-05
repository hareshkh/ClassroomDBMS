package com.ClassroomDBMS.main.templates.Announcement;

import com.ClassroomDBMS.database.announcements.DBAnnouncements;
import com.ClassroomDBMS.main.models.AnnouncementModel;
import com.ClassroomDBMS.main.windows.home.main;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ViewAllAnnouncementsTemplate {

    public static BorderPane getAllAnnouncementsView(String courseId, String faculty_emailId) {
        ArrayList<AnnouncementModel> announcementModels = DBAnnouncements.getAllAnnouncements(courseId, faculty_emailId);

        VBox vBox = new VBox(10);

        Label courseLabel = new Label(courseId);
        courseLabel.setFont(new Font("Cambria", 35));
        courseLabel.setTextFill(Color.web("#fff"));

        vBox.getChildren().add(courseLabel);

        for (AnnouncementModel model : announcementModels) {
            Label message = new Label(model.getMessage());
            message.setWrapText(true);
            message.setFont(new Font("Cambria", 18));
            message.setTextFill(Color.web("#fff"));

            Label details = new Label(model.getTimestamp() + "\n" + model.getAttachment_type() + " : " + model.getAttachment_url());
            details.setFont(new Font("Cambria", 16));
            details.setTextFill(Color.web("#fff"));

            vBox.getChildren().addAll(message, details);

        }

        ScrollPane sc = new ScrollPane();
        sc.setContent(vBox);
        sc.setFitToWidth(true);
        sc.setMaxHeight(main.window.getHeight() - 50);
        main.window.heightProperty().addListener(e -> sc.setMaxHeight(main.window.getHeight() - 50));

        return new BorderPane(sc);

    }

}
