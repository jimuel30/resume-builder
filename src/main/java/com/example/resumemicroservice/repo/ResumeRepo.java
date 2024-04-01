package com.example.resumemicroservice.repo;


import com.example.resumemicroservice.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepo extends JpaRepository<Resume, Long> {
    Optional<Resume> findByResumeIdAndUserId(long resumeId, long userId);

}
