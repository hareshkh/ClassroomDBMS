package com.ClassroomDBMS.database.signIn;

import com.ClassroomDBMS.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbSignUp {

    public static String[] userSignUp(String fullName,String userName,String emailId,String password){
        Connection con = null;

        PreparedStatement stmt = null;
        String query = DBUtils.prepareInsertQuery("classroompopcorn.userdetail", "fullName, username, emailId, password", "?,?,?,?");

        String updateCurrentUserQuery = DBUtils.prepareInsertQuery("classroompopcorn.currentuserlog", "username, fullName, loggedIn", "?,?,?");

        String[] status = {"ongoing","username"};

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, fullName);
            stmt.setString(2, userName);
            stmt.setString(3, emailId);
            stmt.setString(4, password);
            stmt.executeUpdate();
            status[0]="success";
            status[1]=fullName;

            stmt = con.prepareStatement(updateCurrentUserQuery);
            stmt.setString(1, userName);
            stmt.setString(2, status[1]);
            stmt.setString(3, "1");
            stmt.executeUpdate();
        }
        catch(Exception e){
            status[0] = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
