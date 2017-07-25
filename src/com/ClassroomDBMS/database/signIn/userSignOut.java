package com.ClassroomDBMS.database.signIn;

import com.ClassroomDBMS.database.utils.DBUtils;

public class userSignOut {

    public static void userSignOut(String emailId) {
        DBUtils.performAction("DELETE FROM `classroomdbms`.`currentuser` WHERE `emailID`='"+emailId+"';");
    }

}