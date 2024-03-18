package com.example.resumemicroservice.repo;

import com.example.resumemicroservice.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
    List<Skill> findBySkillNameContainingIgnoreCase(String skillName);
    Optional<Skill> findBySkillNameIgnoreCase(String skillName);
}
