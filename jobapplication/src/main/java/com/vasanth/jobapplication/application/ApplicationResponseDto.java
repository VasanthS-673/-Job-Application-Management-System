package com.vasanth.jobapplication.application;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ApplicationResponseDto {

    private Long applicationId;
    private Long jobId;
    private String jobTitle;
    private String company;
    private Long userId;
    private String applicantName;
    private String applicantEmail;
    private String coverLetter;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
}