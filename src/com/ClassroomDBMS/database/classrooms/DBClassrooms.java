package com.ClassroomDBMS.database.classrooms;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.models.ClassroomModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClassrooms {

    public static String newClassroom(String courseId, String faculty_emailId, String course_name, String lecture_timing,
                                      String tutorial_timing, String marks_distribution, String attendance_rule,
                                      String grading_rule, String lecture_hall, String literature_link) {

        String status = "";

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomdbms.classroomdetails",
                "courseId, faculty_emailId, course_name, lecture_timing, tutorial_timing, marks_distribution, attendance_rule, grading_rule, lecture_hall, literature_link",
                "?,?,?,?,?,?,?,?,?,?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, faculty_emailId);
            stmt.setString(3, course_name);
            stmt.setString(4, lecture_timing);
            stmt.setString(5, tutorial_timing);
            stmt.setString(6, marks_distribution);
            stmt.setString(7, attendance_rule);
            stmt.setString(8, grading_rule);
            stmt.setString(9, lecture_hall);
            stmt.setString(10, literature_link);
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

    public static ArrayList<ClassroomModel> getAllClassroomsByEmail(String faculty_emailId) {

        ArrayList<ClassroomModel> result = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.classroomdetails", "faculty_emailId = ?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, faculty_emailId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new ClassroomModel(rs.getString("courseId"), rs.getString("faculty_emailId"),
                        rs.getString("course_name"), rs.getString("lecture_timing"),
                        rs.getString("tutorial_timing"), rs.getString("marks_distribution"),
                        rs.getString("attendance_rule"), rs.getString("grading_rule"),
                        rs.getString("lecture_hall"), rs.getString("literature_link")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return result;
    }

    public static ClassroomModel getClassroomById(String courseId) {
        ClassroomModel classroomModel = null;

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.classroomdetails", "courseId = ?");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                classroomModel = new ClassroomModel(rs.getString("courseId"), rs.getString("faculty_emailId"),
                        rs.getString("course_name"), rs.getString("lecture_timing"),
                        rs.getString("tutorial_timing"), rs.getString("marks_distribution"),
                        rs.getString("attendance_rule"), rs.getString("grading_rule"),
                        rs.getString("lecture_hall"), rs.getString("literature_link"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return classroomModel;
    }

    public static ArrayList<ClassroomModel> getAllClassrooms() {

        ArrayList<ClassroomModel> result = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.classroomdetails", "true");

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new ClassroomModel(rs.getString("courseId"), rs.getString("faculty_emailId"),
                        rs.getString("course_name"), rs.getString("lecture_timing"),
                        rs.getString("tutorial_timing"), rs.getString("marks_distribution"),
                        rs.getString("attendance_rule"), rs.getString("grading_rule"),
                        rs.getString("lecture_hall"), rs.getString("literature_link")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }

        return result;
    }

}
