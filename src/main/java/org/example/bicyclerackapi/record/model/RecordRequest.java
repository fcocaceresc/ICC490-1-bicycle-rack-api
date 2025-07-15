package org.example.bicyclerackapi.record.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "rackId is a required field and can't be null")
    private Long rackId;
    @NotNull(message = "hook is a required field and can't be null")
    private Long hook;

    public RecordRequest(String studentId, String studentName, String bicycleDescription, Long rackId, Long hook) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bicycleDescription = bicycleDescription;
        this.rackId = rackId;
        this.hook = hook;
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

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public Long getHook() {
        return hook;
    }

    public void setHook(Long hook) {
        this.hook = hook;
    }
}
