package com.ClassroomDBMS.main.models;

public class AnnouncementModel {

    private String faculty_emailId;
    private String timestamp;
    private String message;
    private String attachment_type;
    private String attachment_url;

    public AnnouncementModel(String faculty_emailId, String timestamp, String message, String attachment_type, String attachment_url) {
        this.faculty_emailId = faculty_emailId;
        this.timestamp = timestamp;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
