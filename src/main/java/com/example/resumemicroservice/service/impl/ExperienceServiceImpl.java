package com.example.resumemicroservice.service.impl;

import com.example.resumemicroservice.model.Experience;
import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.repo.ExperienceRepo;
import com.example.resumemicroservice.service.ExperienceService;
import com.example.resumemicroservice.service.ResponsibilityService;
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
    public Experience createExperience(final Experience experience) {
        if(Objects.nonNull(experience.getResponsibilityList())){
            final List<Responsibility> responsibilityList = new ArrayList<>();
            for (Responsibility responsibility:experience.getResponsibilityList()) {
                responsibilityList.add(responsibilityService.createResponsibility(responsibility));
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
}
