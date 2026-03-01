package com.vasanth.jobapplication.application;

import com.vasanth.jobapplication.exception.ResourceNotFoundException;
import com.vasanth.jobapplication.job.Job;
import com.vasanth.jobapplication.job.JobRepository;
import com.vasanth.jobapplication.user.User;
import com.vasanth.jobapplication.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    // USER: Apply to a job
    public ApplicationResponseDto apply(String email, ApplyRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Job not found with id: " + request.getJobId()));

        if (!job.isActive()) {
            throw new RuntimeException("This job is no longer active");
        }

        if (applicationRepository.existsByUserIdAndJobId(user.getId(), job.getId())) {
            throw new RuntimeException("You have already applied to this job");
        }

        JobApplication application = JobApplication.builder()
                .user(user)
                .job(job)
                .coverLetter(request.getCoverLetter())
                .status(ApplicationStatus.APPLIED)
                .build();

        return toResponse(applicationRepository.save(application));
    }

    // USER: Get my applications
    public List<ApplicationResponseDto> getMyApplications(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return applicationRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ADMIN: Get all applications for a job
    public List<ApplicationResponseDto> getApplicationsByJob(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("Job not found with id: " + jobId);
        }
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ADMIN: Update application status
    public ApplicationResponseDto updateStatus(Long applicationId,
                                               StatusUpdateRequest request) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id: " + applicationId));

        application.setStatus(request.getStatus());
        return toResponse(applicationRepository.save(application));
    }

    // USER: Withdraw application
    public void withdraw(String email, Long applicationId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id: " + applicationId));

        if (!application.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to withdraw this application");
        }

        applicationRepository.delete(application);
    }

    // Helper: entity → DTO
    private ApplicationResponseDto toResponse(JobApplication app) {
        return ApplicationResponseDto.builder()
                .applicationId(app.getId())
                .jobId(app.getJob().getId())
                .jobTitle(app.getJob().getTitle())
                .company(app.getJob().getCompany())
                .userId(app.getUser().getId())
                .applicantName(app.getUser().getName())
                .applicantEmail(app.getUser().getEmail())
                .coverLetter(app.getCoverLetter())
                .status(app.getStatus())
                .appliedAt(app.getAppliedAt())
                .build();
    }
}