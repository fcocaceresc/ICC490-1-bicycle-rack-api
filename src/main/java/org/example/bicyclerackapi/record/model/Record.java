package org.example.bicyclerackapi.record.model;

import jakarta.persistence.*;
import org.example.bicyclerackapi.rack.model.Rack;

import java.time.Instant;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String studentId;
    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false)
    private String bicycleDescription;
    @Column(nullable = false)
    private Instant checkIn;
    private Instant checkOut;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rack_id", nullable = false)
    private Rack rack;
    @Column(nullable = false)
    private Long hook;

    public Record() {
    }

    public Record(String studentId, String studentName, String bicycleDescription, Rack rack, Long hook) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bicycleDescription = bicycleDescription;
        this.checkIn = Instant.now();
        this.rack = rack;
        this.hook = hook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Instant checkIn) {
        this.checkIn = checkIn;
    }

    public Instant getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Instant checkOut) {
        this.checkOut = checkOut;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public Long getHook() {
        return hook;
    }

    public void setHook(Long hook) {
        this.hook = hook;
    }
}
