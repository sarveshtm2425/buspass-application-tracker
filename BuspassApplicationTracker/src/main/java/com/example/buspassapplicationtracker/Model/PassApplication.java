package com.example.buspassapplicationtracker.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pass_applications")
public class PassApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private BusRoute busRoute;
    private String boardingPoint;
    private String photoReference;
    private String status;
    private String rejectionReason;
    private String passNumber;
    private LocalDate validFrom;
    private LocalDate validUntil;
    public PassApplication() {
    }

    public PassApplication(Student student, BusRoute busRoute,
                           String boardingPoint, String photoReference,
                           String status, String rejectionReason,
                           String passNumber, LocalDate validFrom,
                           LocalDate validUntil) {
        this.student = student;
        this.busRoute = busRoute;
        this.boardingPoint = boardingPoint;
        this.photoReference = photoReference;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.passNumber = passNumber;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }
}