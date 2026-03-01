package com.vasanth.jobapplication.application;

import lombok.Data;

@Data
public class ApplyRequest {
    private Long jobId;
    private String coverLetter;
}