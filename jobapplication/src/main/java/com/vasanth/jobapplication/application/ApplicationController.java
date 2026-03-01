package com.vasanth.jobapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    // USER: Apply to a job
    @PostMapping
    public ResponseEntity<ApplicationResponseDto> apply(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ApplyRequest request) {
        return ResponseEntity.ok(
                applicationService.apply(userDetails.getUsername(), request)
        );
    }

    // USER: View my applications
    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponseDto>> getMyApplications(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                applicationService.getMyApplications(userDetails.getUsername())
        );
    }

    // USER: Withdraw an application
    @DeleteMapping("/{id}")
    public ResponseEntity<String> withdraw(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        applicationService.withdraw(userDetails.getUsername(), id);
        return ResponseEntity.ok("Application withdrawn successfully");
    }

    // ADMIN: View all applications for a job
    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ApplicationResponseDto>> getApplicationsByJob(
            @PathVariable Long jobId) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByJob(jobId)
        );
    }

    // ADMIN: Update application status
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApplicationResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(
                applicationService.updateStatus(id, request)
        );
    }
}