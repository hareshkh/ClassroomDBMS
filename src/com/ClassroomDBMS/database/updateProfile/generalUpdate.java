package com.ClassroomDBMS.database.updateProfile;

import com.ClassroomDBMS.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class generalUpdate {

    public static String generalUpdateStudent(String primaryKey, String firstName, String lastName, String phoneNumber, String college) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        String queryUserDetail = DBUtils.prepareUpdateQuery("classroomdbms.studentdetails", "firstName = ? , lastName = ? , phoneNumber = ? , college = ? ", "emailId = ?");

        String status = "";

        try {
            con = DBUtils.getConnection();

            stmt = con.prepareStatement(queryUserDetail);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, college);
            stmt.setString(5, primaryKey);
            int q1 = stmt.executeUpdate();

            if (q1 != -1)
                status = "Success";
            else
                status = "Failed";
        } catch (Exception e) {
            e.printStackTrace();
            status = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return status;
        }

    }

    public static String generalUpdateFaculty(String primaryKey, String firstName, String lastName, String designation, String phoneNumber) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        String queryUserDetail = DBUtils.prepareUpdateQuery("classroomdbms.facultydetails", "firstName = ? , lastName = ? , designation = ? , phoneNumber = ? ", "emailId = ?");

        String status = "";

        try {
            con = DBUtils.getConnection();

            stmt = con.prepareStatement(queryUserDetail);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, designation);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, primaryKey);
            int q1 = stmt.executeUpdate();

            if (q1 != -1)
                status = "Success";
            else
                status = "Failed";
        } catch (Exception e) {
            e.printStackTrace();
            status = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
        }
        return status;
    }

}
