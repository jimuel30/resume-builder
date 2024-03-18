package com.example.resumemicroservice.repo;

import com.example.resumemicroservice.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExperienceRepo extends JpaRepository<Experience, Long> {
}
