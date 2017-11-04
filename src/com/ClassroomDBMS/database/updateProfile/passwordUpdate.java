package com.ClassroomDBMS.database.updateProfile;

import com.ClassroomDBMS.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class passwordUpdate {

    public static String passwordUpdateQuery(String primaryKey, String oldPassword, String newPassword, String type) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String queryUserDetail = null;

        if (type.equals("student")) {
            queryUserDetail = DBUtils.prepareUpdateQuery("classroomdbms.studentdetails", "password = ? ", "emailId = ? AND password = ?");
        } else if (type.equals("faculty")) {
            queryUserDetail = DBUtils.prepareUpdateQuery("classroomdbms.facultydetails", "password = ? ", "emailId = ? AND password = ?");
        }
        String status = "";

        try {
            con = DBUtils.getConnection();

            stmt = con.prepareStatement(queryUserDetail);
            stmt.setString(1, newPassword);
            stmt.setString(2, primaryKey);
            stmt.setString(3, oldPassword);
            int q1 = stmt.executeUpdate();

            System.out.println(q1);

            if (q1 == 0)
                status = "Old Password was incorrect";
            else
                status = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            status = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
        }
        return status;
    }

}
