package com.ClassroomDBMS.database.logIn;

import com.ClassroomDBMS.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbLoginCheck {

    public static String[] dbLoginCheck(String userName, String password) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroompopcorn.userdetail", "(username = ? OR emailId = ? ) AND password = ?");

        String updateCurrentUserQuery = DBUtils.prepareInsertQuery("classroompopcorn.currentuserlog", "username, fullName, loggedIn", "?,?,?");

        String[] status = {"ongoing","username"};

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, userName);
            stmt.setString(3, password);
            rs = stmt.executeQuery();
            rs.next();
            status[0]="success";
            status[1]=rs.getString("fullName");

            stmt = con.prepareStatement(updateCurrentUserQuery);
            stmt.setString(1, userName);
            stmt.setString(2, status[1]);
            stmt.setString(3, "1");
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return status;
        }
    }
}
