package com.ClassroomDBMS.database.signIn;

import com.ClassroomDBMS.database.utils.DBUtils;

public class userSignOut {

    public static void userSignOut() {
        DBUtils.performAction("DELETE FROM `classroompopcorn`.`currentuserlog` WHERE `loggedIn`='1';");
    }

}
