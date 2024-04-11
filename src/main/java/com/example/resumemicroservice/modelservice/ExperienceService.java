package com.example.resumemicroservice.modelservice;

import com.example.resumemicroservice.model.Experience;
import com.example.resumemicroservice.model.Responsibility;

public interface ExperienceService {
    Experience save(Experience experience);
    Experience getExperienceById(long experienceId);

    Experience delete(long experienceId, long userId);


    Experience update(Experience experience);


}
