package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.Skill;
import org.springframework.http.ResponseEntity;

public interface CreateService {
    ResponseEntity<Response> createSkill(Skill skill, String token);
}
