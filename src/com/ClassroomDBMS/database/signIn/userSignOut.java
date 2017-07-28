package com.ClassroomDBMS.database.signIn;

import com.ClassroomDBMS.database.utils.DBUtils;
import com.ClassroomDBMS.main.functions.getMotherboardSN;

public class userSignOut {

    public static void userSignOut() {
        String userID = getMotherboardSN.getMotherboardSN();
        DBUtils.performAction("DELETE FROM `classroomdbms`.`currentuser` WHERE `id`='"+userID+"';");
    }

}