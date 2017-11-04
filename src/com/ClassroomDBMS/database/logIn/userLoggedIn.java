package com.ClassroomDBMS.database.logIn;

import com.ClassroomDBMS.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userLoggedIn {

    public static String[] userLoggedIn(String id) {
        Connection con = null;
        PreparedStatement stmt_current = null, stmt = null, stmt1 = null;
        ResultSet rs_current = null, rs = null, rs1 = null;

        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.currentuser", "id = '" + id + "'");

        String facultyQuery = DBUtils.prepareSelectQuery(" * ", "classroomdbms.facultydetails", "emailId = ?");
        String studentQuery = DBUtils.prepareSelectQuery(" * ", "classroomdbms.studentdetails", "emailId = ?");


        String[] status = new String[10];

        try {
            con = DBUtils.getConnection();
            stmt_current = con.prepareStatement(query);
            rs_current = stmt_current.executeQuery();
            rs_current.next();

            stmt = con.prepareStatement(facultyQuery);
            stmt.setString(1, rs_current.getString("emailId"));
            rs = stmt.executeQuery();

            stmt1 = con.prepareStatement(studentQuery);
            stmt1.setString(1, rs_current.getString("emailId"));
            rs1 = stmt1.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            rs1.last();
            int size1 = rs1.getRow();
            rs1.beforeFirst();

            if (size > 0) {
                rs.next();
                status[0] = "success";
                status[1] = "faculty";
                status[2] = rs.getString("firstName");
                status[3] = rs.getString("lastName");
                status[4] = rs.getString("emailId");
                status[5] = rs.getString("designation");
                status[6] = rs.getString("phoneNumber");
            } else if (size1 > 0) {
                rs1.next();
                status[0] = "success";
                status[1] = "student";
                status[2] = rs1.getString("firstName");
                status[3] = rs1.getString("lastName");
                status[4] = rs1.getString("emailId");
                status[5] = rs1.getString("dob");
                status[6] = rs1.getString("phoneNumber");
                status[7] = rs1.getString("gender");
                status[8] = rs1.getString("college");
            }
        } catch (Exception e) {
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs_current, stmt_current, con);
            DBUtils.closeAll(rs, stmt, con);
            DBUtils.closeAll(rs1, stmt1, con);
            return status;
        }
    }
}
