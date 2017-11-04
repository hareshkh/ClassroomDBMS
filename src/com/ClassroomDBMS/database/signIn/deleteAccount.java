package com.ClassroomDBMS.database.signIn;

import com.ClassroomDBMS.database.utils.DBUtils;

public class deleteAccount {

    public static void deleteStudentAccount(String emailId) {
        DBUtils.performAction("DELETE FROM `classroomdbms`.`studentdetails` WHERE `emailID`='"+emailId+"';");
        DBUtils.performAction("DELETE FROM `classroomdbms`.`currentuser` WHERE `emailID`='"+emailId+"';");
    }
    public static void deleteFacultyAccount(String emailId) {
        DBUtils.performAction("DELETE FROM `classroomdbms`.`facultydetails` WHERE `emailID`='"+emailId+"';");
        DBUtils.performAction("DELETE FROM `classroomdbms`.`currentuser` WHERE `emailID`='"+emailId+"';");
    }
}
