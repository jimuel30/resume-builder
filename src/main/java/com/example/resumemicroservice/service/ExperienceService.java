package com.example.resumemicroservice.service;

import com.example.resumemicroservice.model.Experience;

public interface ExperienceService {
    Experience createExperience(Experience experience);
    Experience getExperienceById(long experienceId);
}
