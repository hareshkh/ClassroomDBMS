package com.ClassroomDBMS.main.models;

public class AssignmentModel {

    private String faculty_emailId;
    private String timestamp;
    private String deadline;
    private String assignment_details;
    private String attachment_type;
    private String attachment_url;

    public AssignmentModel(String faculty_emailId, String timestamp, String deadline, String assignment_details, String attachment_type, String attachment_url) {
        this.faculty_emailId = faculty_emailId;
        this.timestamp = timestamp;
        this.deadline = deadline;
        this.assignment_details = assignment_details;
        this.attachment_type = attachment_type;
        this.attachment_url = attachment_url;
    }

    public String getFaculty_emailId() {
        return faculty_emailId;
    }

    public void setFaculty_emailId(String faculty_emailId) {
        this.faculty_emailId = faculty_emailId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAssignment_details() {
        return assignment_details;
    }

    public void setAssignment_details(String assignment_details) {
        this.assignment_details = assignment_details;
    }

    public String getAttachment_type() {
        return attachment_type;
    }

    public void setAttachment_type(String attachment_type) {
        this.attachment_type = attachment_type;
    }

    public String getAttachment_url() {
        return attachment_url;
    }

    public void setAttachment_url(String attachment_url) {
        this.attachment_url = attachment_url;
    }
}
