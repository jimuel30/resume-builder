package com.example.resumemicroservice.modelservice.impl;

import com.example.resumemicroservice.model.Experience;
import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.repo.ExperienceRepo;
import com.example.resumemicroservice.modelservice.ExperienceService;
import com.example.resumemicroservice.modelservice.ResponsibilityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepo experienceRepo;
    private final ResponsibilityService responsibilityService;

    public ExperienceServiceImpl(final ExperienceRepo experienceRepo,
                                 final ResponsibilityService responsibilityService) {
        this.experienceRepo = experienceRepo;
        this.responsibilityService = responsibilityService;
    }

    @Override
    public Experience save(final Experience experience) {
        if(Objects.nonNull(experience.getResponsibilityList())){
            final List<Responsibility> responsibilityList = new ArrayList<>();
            for (Responsibility responsibility:experience.getResponsibilityList()) {
                responsibilityList.add(responsibilityService.save(responsibility));
            }
            experience.setResponsibilityList(responsibilityList);
        }
        return experienceRepo.save(experience);
    }

    @Override
    public Experience getExperienceById(long experienceId) {
        final Optional<Experience> optionalExperience = experienceRepo.findById(experienceId);
        return optionalExperience.orElse(null);
    }

    @Override
    public Experience delete(final long experienceId, final long userId) {
        final Optional<Experience> experienceOptional = experienceRepo.findByExperienceIdAndUserId(experienceId,userId);
        Experience deletedExperience =null;
        if(experienceOptional.isPresent()){
            deletedExperience  =  experienceOptional.get();
            experienceRepo.delete(deletedExperience);
        }
        return deletedExperience;
    }

    @Override
    public Responsibility deleteResponsibility(final long experienceId,
                                               final long responsibilityId,
                                               final long userId) {

        final Optional<Experience> experienceOptional = experienceRepo.findByExperienceIdAndUserId(experienceId,userId);
        Responsibility deletedResponsibility = null;
        if(experienceOptional.isPresent()){
            deletedResponsibility = responsibilityService.delete(responsibilityId,experienceId);
        }
        return deletedResponsibility;
    }
}
