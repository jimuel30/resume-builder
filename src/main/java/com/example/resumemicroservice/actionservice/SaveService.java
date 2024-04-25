package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SaveService {
    ResponseEntity<Response> saveSkill(Skill skill, String token, long resumeId);
    ResponseEntity<Response> saveExperience(Experience experience, String token, long resumeId);

    ResponseEntity<Response> saveEducation(Education education, String token, long resumeId);

    ResponseEntity<Response> saveResponsibility(Responsibility responsibility, String token, long resumeId);

    ResponseEntity<Response> saveSocial(Social social, String token, long resumeId);

    ResponseEntity<Response> saveResume(String token);

    ResponseEntity<Response> batchSaveSkill(List<Skill> skillList, String token, long resumeId);
}
