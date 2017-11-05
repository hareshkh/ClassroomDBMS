package com.ClassroomDBMS.database.submissions;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.models.SubmissionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBSubmissions {

    public static String submitAssignment(int assignmentId, String studentEmailId, String timestamp, String submission) {

        String status = "";

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomdbms.studentsubmissiondetails", "emailId, assignmentId, timestamp, submission", "?,?,?,?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, studentEmailId);
            stmt.setInt(2, assignmentId);
            stmt.setString(3, timestamp);
            stmt.setString(4, submission);
            stmt.executeUpdate();

            status = "success";

        } catch (SQLException e) {
            e.printStackTrace();
            status = "failed";
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return status;

    }

    public static ArrayList<SubmissionModel> getSubmissions(int assignmentId) {

        ArrayList<SubmissionModel> submissionModels = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.studentsubmissiondetails", "assignmentId = ?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, assignmentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                submissionModels.add(new SubmissionModel(rs.getInt("assignmentId"),
                        rs.getString("emailId"),
                        rs.getString("timestamp"),
                        rs.getString("submission")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return submissionModels;

    }

}
