package com.ClassroomDBMS.database.tutorialSubmission;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.templates.tutorialSubmission.message;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchTutorials {

    public static String latestestTut ;

    public static VBox fetchTutorials(String currentUserMailId) {

        VBox noticeList = new VBox();

        Connection con = null;
        PreparedStatement tutorialsstmt = null ;
        ResultSet tutorialrs = null ;
        PreparedStatement submissionstmt = null;
        ResultSet submissionrs = null ;

        String tutorialsquery = DBUtils.prepareSelectQuery(" * ", "classroomdbms.tutorials", "","ORDER BY timestamp asc" );

        String submissionsquery = DBUtils.prepareSelectQuery(" * ", "classroomdbms.submissions", "( tutorialsName = ? AND emailID = '"+currentUserMailId+"' ) ","ORDER BY timestamp asc;" );

        try {
            con = DBUtils.getConnection();
            tutorialsstmt = con.prepareStatement(tutorialsquery);

            tutorialrs = tutorialsstmt.executeQuery();

            while (tutorialrs.next()){
                String timestamp = tutorialrs.getString("timestamp");
                String tutorialsName = tutorialrs.getString("tutorialsName");
                latestestTut = tutorialsName;
                String deadline = tutorialrs.getString("deadline");
                String question = tutorialrs.getString("question");

                StackPane results = new StackPane();
                results.setStyle("-fx-background-color: #171717");
                results.setPadding(new Insets(5,0,5,0));
                Label result = new Label("-----------"+tutorialsName+"-----------");
                result.setFont(new Font("Cambria", 20));
                result.setTextFill(Color.web("#fff"));
                result.setPadding(new Insets(10));
                results.getChildren().add(result);
                noticeList.getChildren().add(results);

                noticeList.getChildren().add(message.leftformatmessage(timestamp, deadline, question));

                try {
                    submissionstmt = con.prepareStatement(submissionsquery);
                    submissionstmt.setString(1,tutorialsName);

                    submissionrs = submissionstmt.executeQuery();

                    while (submissionrs.next()) {
                        String submittimestamp = submissionrs.getString("timestamp");
                        String submitanswer = submissionrs.getString("answer");

                        noticeList.getChildren().add(message.rightformatmessage(submittimestamp, submitanswer));
                    }
                } catch (Exception e) {
//                    noticeList.getChildren().add(message.errorformatmessage());
                } finally {
                    DBUtils.closeResultSet(submissionrs);
                    DBUtils.closeStatement(submissionstmt);
                }
            }

        } catch (Exception e) {
            noticeList.getChildren().add(message.errorformatmessage());
        } finally {
            DBUtils.closeAll(tutorialrs, tutorialsstmt, con);
            return noticeList;
        }
    }

}
