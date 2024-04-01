package com.example.resumemicroservice.repo;


import com.example.resumemicroservice.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExperienceRepo extends JpaRepository<Experience, Long> {
    Optional<Experience> findByExperienceIdAndUserId(long experienceId, long userId);
}
