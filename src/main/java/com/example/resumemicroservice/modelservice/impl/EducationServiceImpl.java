package com.example.resumemicroservice.modelservice.impl;

import com.example.resumemicroservice.model.Education;
import com.example.resumemicroservice.repo.EducationRepo;
import com.example.resumemicroservice.modelservice.EducationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepo educationRepo;

    public EducationServiceImpl(final EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

    @Override
    public Education save(final Education education) {
        return educationRepo.save(education);
    }

    @Override
    public Education delete(final long educationId, final long userId) {
        final Optional<Education> educationOptional = educationRepo.findByEducationIdAndUserId(educationId,userId);
        Education deletedEducation =null;
        if(educationOptional.isPresent()){
            deletedEducation =  educationOptional.get();
            educationRepo.delete(deletedEducation);
        }
        return deletedEducation;
    }
}
