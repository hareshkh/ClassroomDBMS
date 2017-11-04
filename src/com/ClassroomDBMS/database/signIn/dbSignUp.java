package com.ClassroomDBMS.database.signIn;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.functions.getMotherboardSN;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbSignUp {

    public static String[] facultySignUp(String firstName, String lastName, String emailId, String password, String designation, String phoneNumber) {
        Connection con = null;

        PreparedStatement stmt = null;
        String query = DBUtils.prepareInsertQuery("classroomdbms.facultydetails", "firstName, lastName, emailId, password, designation, phoneNumber", "?,?,?,?,?,?");

        String userID = getMotherboardSN.getMotherboardSN();

        String updateCurrentUserQuery = DBUtils.prepareReplaceQuery("classroomdbms.currentuser", "id, emailId, type", "?,?,?");

        String[] status = new String[10];
        status[1] = "faculty";
        status[2] = firstName;
        status[3] = lastName;
        status[4] = emailId;
        status[5] = designation;
        status[6] = phoneNumber;

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, emailId);
            stmt.setString(4, password);
            stmt.setString(5, designation);
            stmt.setString(6, phoneNumber);
            stmt.executeUpdate();
            status[0] = "success";

            stmt = con.prepareStatement(updateCurrentUserQuery);
            stmt.setString(1, userID);
            stmt.setString(2, emailId);
            stmt.setString(3, "faculty");
            stmt.executeUpdate();
        } catch (Exception e) {
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
        }
        return status;
    }

    public static String[] studentSignUp(String firstName, String lastName, String emailId, String password, String dob, String phoneNumber, String gender, String college) {
        Connection con = null;

        PreparedStatement stmt = null;
        String query = DBUtils.prepareInsertQuery("classroomdbms.studentdetails", "firstName, lastName, emailId, password, dob, phoneNumber, gender, college", "?,?,?,?,?,?,?,?");

        String userID = getMotherboardSN.getMotherboardSN();

        String updateCurrentUserQuery = DBUtils.prepareReplaceQuery("classroomdbms.currentuser", "id, emailId, type", "?,?,?");

        String[] status = new String[10];
        status[1] = "student";
        status[2] = firstName;
        status[3] = lastName;
        status[4] = emailId;
        status[5] = dob;
        status[6] = phoneNumber;
        status[7] = gender;
        status[8] = college;

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, emailId);
            stmt.setString(4, password);
            stmt.setString(5, dob);
            stmt.setString(6, phoneNumber);
            stmt.setString(7, gender);
            stmt.setString(8, college);
            stmt.executeUpdate();
            status[0] = "success";

            stmt = con.prepareStatement(updateCurrentUserQuery);
            stmt.setString(1, userID);
            stmt.setString(2, emailId);
            stmt.setString(3, "student");
            stmt.executeUpdate();
        } catch (Exception e) {
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
