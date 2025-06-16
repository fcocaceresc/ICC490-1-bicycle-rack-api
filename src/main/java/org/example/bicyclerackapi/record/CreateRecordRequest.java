package org.example.bicyclerackapi.record;

public class CreateRecordRequest {
    private String studentId;
    private String studentName;
    private String bicycleDescription;

    public CreateRecordRequest(String studentId, String studentName, String bicycleDescription) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bicycleDescription = bicycleDescription;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBicycleDescription() {
        return bicycleDescription;
    }

    public void setBicycleDescription(String bicycleDescription) {
        this.bicycleDescription = bicycleDescription;
    }
}
