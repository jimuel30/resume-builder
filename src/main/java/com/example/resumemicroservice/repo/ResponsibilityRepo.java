package com.example.resumemicroservice.repo;

import com.example.resumemicroservice.model.Responsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibilityRepo extends JpaRepository<Responsibility, Long> {
}
