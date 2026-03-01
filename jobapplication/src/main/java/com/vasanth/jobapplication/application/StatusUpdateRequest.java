package com.vasanth.jobapplication.application;

import lombok.Data;

@Data
public class StatusUpdateRequest {
    private ApplicationStatus status;
}