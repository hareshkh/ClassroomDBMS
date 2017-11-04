package com.ClassroomDBMS.database.assignments;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.models.AssignmentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<AssignmentModel> getAllAssignments(String courseId, String faculty_emailId) {
        ArrayList<AssignmentModel> assignmentModels = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.assignments", "courseId = ? AND faculty_emailId = ?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, faculty_emailId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                assignmentModels.add(new AssignmentModel(rs.getString("courseId"), rs.getString("faculty_emailId"),
                        rs.getString("timestamp"), rs.getString("deadline"),
                        rs.getString("assignment_details"), rs.getString("attachment_type"),
                        rs.getString("attachment_url")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return assignmentModels;
    }

}
