package com.example.resumemicroservice.repo;


import com.example.resumemicroservice.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepo extends JpaRepository<Education, Long> {

    Optional<Education> findByEducationIdAndUserId(long educationId,long userId);
}
