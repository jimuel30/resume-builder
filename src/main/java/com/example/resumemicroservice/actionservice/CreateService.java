package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.Experience;
import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.model.Skill;
import org.springframework.http.ResponseEntity;

public interface CreateService {
    ResponseEntity<Response> createSkill(Skill skill, String token);
    ResponseEntity<Response> createExperience(Experience experience, String token);

    ResponseEntity<Response> createResponsibility(Responsibility responsibility,long experienceId, String token);
}
