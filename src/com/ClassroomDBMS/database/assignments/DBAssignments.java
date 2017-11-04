package com.ClassroomDBMS.database.assignments;

import com.ClassroomDBMS.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAssignments {

    public static String postAssignment(String courseId, String faculty_emailId, String timestamp, String deadline, String assignment_details, String attachment_type, String attachment_url) {

        String status = "";

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomdbms.assignments", "courseId, faculty_emailId, timestamp, deadline, assignment_details, attachment_type, attachment_url", "?,?,?,?,?,?,?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, faculty_emailId);
            stmt.setString(3, timestamp);
            stmt.setString(4, deadline);
            stmt.setString(5, assignment_details);
            stmt.setString(6, attachment_type);
            stmt.setString(7, attachment_url);
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

}
