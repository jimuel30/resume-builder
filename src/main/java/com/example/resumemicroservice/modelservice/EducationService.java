package com.example.resumemicroservice.modelservice;

import com.example.resumemicroservice.model.Education;

public interface EducationService {
    Education save(Education education);
    Education delete(long educationId, long userId);

    Education update(Education education);
}
