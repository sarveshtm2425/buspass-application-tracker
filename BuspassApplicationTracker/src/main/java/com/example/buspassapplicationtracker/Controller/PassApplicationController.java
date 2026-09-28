package com.example.buspassapplicationtracker.Controller;

import com.example.buspassapplicationtracker.Model.PassApplication;
import com.example.buspassapplicationtracker.Service.PassApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class PassApplicationController {

    private final PassApplicationService passApplicationService;

    public PassApplicationController(PassApplicationService passApplicationService) {
        this.passApplicationService = passApplicationService;
    }

    @PostMapping
    public ResponseEntity<PassApplication> createApplication(
            @RequestBody PassApplication passApplication) {

        return ResponseEntity.ok(
                passApplicationService.createApplication(passApplication)
        );
    }

    @GetMapping
    public ResponseEntity<List<PassApplication>> getAllApplications() {

        return ResponseEntity.ok(
                passApplicationService.getAllApplications()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassApplication> getApplicationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                passApplicationService.getApplicationById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody PassApplication passApplication) {

        return ResponseEntity.ok(
                passApplicationService.updateApplication(id, passApplication)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable Long id) {

        passApplicationService.deleteApplication(id);

        return ResponseEntity.ok(
                "Pass application deleted successfully"
        );
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<PassApplication> approveApplication(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                passApplicationService.approveApplication(id)
        );
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<PassApplication> rejectApplication(
            @PathVariable Long id,
            @RequestParam String reason) {

        return ResponseEntity.ok(
                passApplicationService.rejectApplication(id, reason)
        );
    }

    @GetMapping("/expiring")
    public ResponseEntity<List<PassApplication>> getExpiringApplications() {

        return ResponseEntity.ok(
                passApplicationService.getExpiringApplications()
        );
    }
}