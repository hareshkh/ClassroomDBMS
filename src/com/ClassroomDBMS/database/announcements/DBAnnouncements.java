package com.ClassroomDBMS.database.announcements;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.models.AnnouncementModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAnnouncements {

    public static String postAnnouncement(String courseId, String faculty_emailId, String timestamp, String message, String attachment_type, String attachment_url) {

        String status = "";

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomdbms.announcements", "courseId, faculty_emailId, timestamp, message, attachment_type, attachment_url", "?,?,?,?,?,?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, faculty_emailId);
            stmt.setString(3, timestamp);
            stmt.setString(4, message);
            stmt.setString(5, attachment_type);
            stmt.setString(6, attachment_url);
            stmt.executeUpdate();

            status = "success";

        } catch (SQLException e) {
            e.printStackTrace();
            status = e.getMessage();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return status;
    }

    public static ArrayList<AnnouncementModel> getAllAnnouncements(String courseId, String faculty_emailId) {
        ArrayList<AnnouncementModel> announcementModels = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.announcements", "courseId = ? AND faculty_emailId = ?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, faculty_emailId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                announcementModels.add(new AnnouncementModel(rs.getString("courseId"), rs.getString("faculty_emailId"),
                        rs.getString("timestamp"), rs.getString("message"),
                        rs.getString("attachment_type"),
                        rs.getString("attachment_url")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return announcementModels;
    }

}
