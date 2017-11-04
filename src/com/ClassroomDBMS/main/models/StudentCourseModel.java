package com.ClassroomDBMS.main.models;

public class StudentCourseModel {

    private String studentEmailId;
    private String courseId;

    public StudentCourseModel(String studentEmailId, String courseId) {
        this.studentEmailId = studentEmailId;
        this.courseId = courseId;
    }

    public String getStudentEmailId() {
        return studentEmailId;
    }

    public void setStudentEmailId(String studentEmailId) {
        this.studentEmailId = studentEmailId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
