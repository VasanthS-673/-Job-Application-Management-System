package com.vasanth.jobapplication.application;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByUserId(Long userId);

    List<JobApplication> findByJobId(Long jobId);

    boolean existsByUserIdAndJobId(Long userId, Long jobId);

    Optional<JobApplication> findByUserIdAndJobId(Long userId, Long jobId);
}