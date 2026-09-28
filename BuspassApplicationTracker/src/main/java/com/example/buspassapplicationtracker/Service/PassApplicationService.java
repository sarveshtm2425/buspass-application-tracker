package com.example.buspassapplicationtracker.Service;

import com.example.buspassapplicationtracker.Model.BusRoute;
import com.example.buspassapplicationtracker.Model.PassApplication;
import com.example.buspassapplicationtracker.Model.Student;
import com.example.buspassapplicationtracker.Repository.BusRouteRepository;
import com.example.buspassapplicationtracker.Repository.PassApplicationRepository;
import com.example.buspassapplicationtracker.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PassApplicationService {

    private final PassApplicationRepository passApplicationRepository;
    private final StudentRepository studentRepository;
    private final BusRouteRepository busRouteRepository;

    public PassApplicationService(
            PassApplicationRepository passApplicationRepository,
            StudentRepository studentRepository,
            BusRouteRepository busRouteRepository) {

        this.passApplicationRepository = passApplicationRepository;
        this.studentRepository = studentRepository;
        this.busRouteRepository = busRouteRepository;
    }

    public PassApplication createApplication(PassApplication passApplication) {

        if (passApplication.getStudent() == null ||
                passApplication.getStudent().getId() == null) {

            throw new IllegalArgumentException("Student ID is required.");
        }

        if (passApplication.getBusRoute() == null ||
                passApplication.getBusRoute().getId() == null) {

            throw new IllegalArgumentException("Route ID is required.");
        }

        if (passApplication.getBoardingPoint() == null ||
                passApplication.getBoardingPoint().trim().isEmpty()) {

            throw new IllegalArgumentException("Boarding point is required.");
        }

        if (passApplication.getPhotoReference() == null ||
                passApplication.getPhotoReference().trim().isEmpty()) {

            throw new IllegalArgumentException("Photo reference is required.");
        }

        Long studentId = passApplication.getStudent().getId();
        Long routeId = passApplication.getBusRoute().getId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Student with ID " + studentId + " was not found."
                        ));

        BusRoute busRoute = busRouteRepository.findById(routeId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Bus route with ID " + routeId + " was not found."
                        ));

        passApplication.setStudent(student);
        passApplication.setBusRoute(busRoute);

        if (passApplication.getStatus() == null ||
                passApplication.getStatus().trim().isEmpty()) {

            passApplication.setStatus("PENDING");
        }

        return passApplicationRepository.save(passApplication);
    }

    public List<PassApplication> getAllApplications() {
        return passApplicationRepository.findAll();
    }

    public PassApplication getApplicationById(Long id) {
        return passApplicationRepository.findById(id).orElse(null);
    }

    public PassApplication updateApplication(Long id, PassApplication passApplication) {

        PassApplication existingApplication =
                passApplicationRepository.findById(id).orElse(null);

        if (existingApplication != null) {

            existingApplication.setStudent(passApplication.getStudent());
            existingApplication.setBusRoute(passApplication.getBusRoute());
            existingApplication.setBoardingPoint(passApplication.getBoardingPoint());
            existingApplication.setPhotoReference(passApplication.getPhotoReference());
            existingApplication.setStatus(passApplication.getStatus());
            existingApplication.setRejectionReason(passApplication.getRejectionReason());
            existingApplication.setPassNumber(passApplication.getPassNumber());
            existingApplication.setValidFrom(passApplication.getValidFrom());
            existingApplication.setValidUntil(passApplication.getValidUntil());

            return passApplicationRepository.save(existingApplication);
        }

        return null;
    }

    public void deleteApplication(Long id) {
        passApplicationRepository.deleteById(id);
    }

    public PassApplication approveApplication(Long id) {

        PassApplication application =
                passApplicationRepository.findById(id).orElse(null);

        if (application != null) {

            application.setStatus("APPROVED");
            application.setPassNumber("PASS-" + application.getId());
            application.setValidFrom(LocalDate.now());
            application.setValidUntil(LocalDate.now().plusDays(365));

            return passApplicationRepository.save(application);
        }

        return null;
    }

    public PassApplication rejectApplication(Long id, String reason) {

        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Rejection reason is required."
            );
        }

        PassApplication application =
                passApplicationRepository.findById(id).orElse(null);

        if (application != null) {

            application.setStatus("REJECTED");
            application.setRejectionReason(reason);

            return passApplicationRepository.save(application);
        }

        return null;
    }

    public List<PassApplication> getExpiringApplications() {

        LocalDate today = LocalDate.now();
        LocalDate next30Days = today.plusDays(30);

        return passApplicationRepository.findAll()
                .stream()
                .filter(application ->
                        application.getValidUntil() != null &&
                                !application.getValidUntil().isBefore(today) &&
                                !application.getValidUntil().isAfter(next30Days))
                .toList();
    }
}