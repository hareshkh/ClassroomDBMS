package com.ClassroomDBMS.database.logIn;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.functions.getMotherboardSN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbLoginCheck {

    public static String[] dbLoginCheck(String emailId, String password) {
        Connection con = null;
        PreparedStatement stmt = null, stmt1 = null;
        ResultSet rs = null, rs1 = null;

        String facultyQuery = DBUtils.prepareSelectQuery(" * ", "classroomdbms.facultydetails", "emailId = ? AND password = ?");
        String studentQuery = DBUtils.prepareSelectQuery(" * ", "classroomdbms.studentdetails", "emailId = ? AND password = ?");

        String userID = getMotherboardSN.getMotherboardSN();

        String updateCurrentUserQuery = DBUtils.prepareReplaceQuery("classroomdbms.currentuser", "id, emailId, type", "?,?,?");

        String[] status = new String[10];

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(facultyQuery);
            stmt.setString(1, emailId);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            stmt1 = con.prepareStatement(studentQuery);
            stmt1.setString(1, emailId);
            stmt1.setString(2, password);
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

                stmt = con.prepareStatement(updateCurrentUserQuery);
                stmt.setString(1, userID);
                stmt.setString(2, status[4]);
                stmt.setString(3, "faculty");
                stmt.executeUpdate();
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

                stmt = con.prepareStatement(updateCurrentUserQuery);
                stmt.setString(1, userID);
                stmt.setString(2, status[4]);
                stmt.setString(3, "student");
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
            status[0] = e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            DBUtils.closeAll(rs1, stmt1, con);
            return status;
        }
    }
}
