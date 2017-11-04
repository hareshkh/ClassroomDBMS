package com.ClassroomDBMS.database.peopleSearch;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.templates.search.searchResult;

import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class searchByKeyword {

    public static VBox searchByKeyword(String keyword) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String whereClause = "(firstName LIKE '%" + keyword + "%' OR lastName LIKE '%" + keyword + "%' OR emailId LIKE '%" + keyword + "%' OR phoneNumber LIKE '%" + keyword + "%' OR college LIKE '%" + keyword + "%')";

        String otherClause = "ORDER BY TRIM(firstName) asc, TRIM(lastName) asc";
        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.studentdetails", whereClause, otherClause);

        VBox searchStack = new VBox(10);

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size == 0) {
                searchStack.getChildren().add(searchResult.searchResult());
                return searchStack;
            }

            String firstName;
            String lastName;
            String emailId;
            String phoneNumber;
            String college;

            while (rs.next()) {
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                emailId = rs.getString("emailId");
                phoneNumber = rs.getString("phoneNumber");
                college = rs.getString("college");

                searchStack.getChildren().add(searchResult.searchResult(firstName, lastName, emailId, phoneNumber, college));
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        } finally {
            DBUtils.closeAll(rs, stmt, con);
        }

        return searchStack;
    }

}
