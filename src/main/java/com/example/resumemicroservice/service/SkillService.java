package com.example.resumemicroservice.service;

import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.Skill;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SkillService {
    Skill createSkill(Skill skill);

    List<Skill> searchSkill(String skillPrefix);


}
