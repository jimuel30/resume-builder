package com.example.resumemicroservice.actionservice.impl;

import com.example.resumemicroservice.actionservice.UpdateService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Override
    public ResponseEntity<Response> updateSkill(Skill skill, String token, long resumeId) {
        return null;
    }

    @Override
    public ResponseEntity<Response> updateExperience(Experience experience, String token, long resumeId) {
        return null;
    }

    @Override
    public ResponseEntity<Response> updateEducation(Education education, String token, long resumeId) {
        return null;
    }

    @Override
    public ResponseEntity<Response> updateResponsibility(Responsibility responsibility, String token, long resumeId) {
        return null;
    }

    @Override
    public ResponseEntity<Response> updateSocial(Social social, String token, long resumeId) {
        return null;
    }

    @Override
    public ResponseEntity<Response> updateResume(String token) {
        return null;
    }
}
