package com.ClassroomDBMS.main.models;

public class SubmissionModel {

    private int assignmentId;
    private String studentEmailId;
    private String timestamp;
    private String submission;

    public SubmissionModel(int assignmentId, String studentEmailId, String timestamp, String submission) {
        this.assignmentId = assignmentId;
        this.studentEmailId = studentEmailId;
        this.timestamp = timestamp;
        this.submission = submission;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getStudentEmailId() {
        return studentEmailId;
    }

    public void setStudentEmailId(String studentEmailId) {
        this.studentEmailId = studentEmailId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }
}
