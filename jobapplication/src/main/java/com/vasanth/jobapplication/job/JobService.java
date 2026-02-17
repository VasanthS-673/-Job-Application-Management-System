package com.vasanth.jobapplication.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;


    public Job createJob(Job job) {
        job.setActive(true);
        return jobRepository.save(job);
    }


    public List<Job> getAllActiveJobs() {
        return jobRepository.findByActiveTrue();
    }


    public void deleteJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        jobRepository.delete(job);
    }
}
