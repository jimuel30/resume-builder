package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.*;
import org.springframework.http.ResponseEntity;

public interface UpdateService {
    ResponseEntity<Response> updateSkill(Skill skill, String token, long resumeId);
    ResponseEntity<Response> updateExperience(Experience experience, String token, long resumeId);

    ResponseEntity<Response> updateEducation(Education education, String token, long resumeId);

    ResponseEntity<Response> updateResponsibility(Responsibility responsibility, String token, long resumeId);

    ResponseEntity<Response> updateSocial(Social social, String token, long resumeId);

    ResponseEntity<Response> updateResume(String token);
}
