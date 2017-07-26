package com.ClassroomDBMS.database.tutorialSubmission;

import com.ClassroomDBMS.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addNewTutorial {

    public static String add(String timeStamp, String tutorialsName, String emailId, String answer){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomdbms.submissions", "timestamp, tutorialsName,emailId, answer", "?,?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, tutorialsName);
            stmt.setString(3, emailId);
            stmt.setString(4, answer);

            stmt.executeUpdate();
            status ="success";
        }
        catch(Exception e){
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
