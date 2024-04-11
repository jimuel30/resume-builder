package com.example.resumemicroservice.repo;


import com.example.resumemicroservice.model.Responsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsibilityRepo extends JpaRepository<Responsibility, Long> {

    Optional<Responsibility> findByResponsibilityIdAndExperienceId(long responsibilityId, long experienceId);
    Optional<Responsibility> findByResponsibilityIdAndUserId(long responsibilityId, long userId);
}
