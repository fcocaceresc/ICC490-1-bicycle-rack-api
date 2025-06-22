package org.example.bicyclerackapi.exception;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecordRequest {
    @NotBlank(message = "studentId is a required field and can't be null or empty")
    @Size(max = 255, message = "studentId must be less than or equal to 255 characters")
    private String studentId;
    @NotBlank(message = "studentName is a required field and can't be null or empty")
    @Size(max = 255, message = "studentName must be less than or equal to 255 characters")
    private String studentName;
    @NotBlank(message = "bicycleDescription is a required field and can't be null or empty")
    @Size(max = 255, message = "bicycleDescription must be less than or equal to 255 characters")
    private String bicycleDescription;

    public RecordRequest(String studentId, String studentName, String bicycleDescription) {
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
