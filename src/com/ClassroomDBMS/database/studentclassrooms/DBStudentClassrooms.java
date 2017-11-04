package com.ClassroomDBMS.database.studentclassrooms;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.models.StudentCourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBStudentClassrooms {

    public static void newStudentClassroom(String studentEmailId, String courseId) {

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomdbms.studentcoursedetails", "emailId, courseId", "?,?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, studentEmailId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

    }

    public static ArrayList<StudentCourseModel> getAllCoursesByEmailId(String emailId) {
        ArrayList<StudentCourseModel> studentCourseModels = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.studentcoursedetails", "emailId = ?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, emailId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                studentCourseModels.add(new StudentCourseModel(rs.getString("emailId"),
                        rs.getString("courseId")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return studentCourseModels;
    }

}
