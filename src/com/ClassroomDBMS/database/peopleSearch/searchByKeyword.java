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

        String whereClause = "(fullName LIKE '%"+keyword+"%' OR emailId LIKE '%"+keyword+"%' OR phoneNumber LIKE '%"+keyword+"%' OR college LIKE '%"+keyword+"%')";

        String otherClause = "ORDER BY fullName asc";
        String query = DBUtils.prepareSelectQuery(" * ", "classroomdbms.userdetail", whereClause, otherClause);

        VBox searchStack = new VBox(10);

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size==0)
                searchStack.getChildren().add(searchResult.searchResult());

            String fullName;
            String emailId;
            String phoneNumber;
            String college;

            while (rs.next()) {
                fullName = rs.getString("fullName");
                emailId = rs.getString("emailId");
                phoneNumber = rs.getString("phoneNumber");
                college = rs.getString("college");

                searchStack.getChildren().add(searchResult.searchResult(fullName,emailId,phoneNumber,college));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
        }

        return searchStack;
    }

}
