package com.example.resumemicroservice.modelservice.impl;

import com.example.resumemicroservice.model.Experience;
import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.repo.ResponsibilityRepo;
import com.example.resumemicroservice.modelservice.ResponsibilityService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ResponsibilityServiceImpl implements ResponsibilityService {

    private final ResponsibilityRepo responsibilityRepo;

    public ResponsibilityServiceImpl(ResponsibilityRepo responsibilityRepo) {
        this.responsibilityRepo = responsibilityRepo;
    }

    @Override
    public Responsibility save(Responsibility responsibility) {
        Responsibility savedResponsibility = update(responsibility);
        if(!Objects.nonNull(savedResponsibility)){
            savedResponsibility = responsibilityRepo.save(responsibility);
        }
        return savedResponsibility;
    }

    @Override
    public Responsibility delete(long responsibilityId, long experienceId) {
        final Optional<Responsibility> responsibilityOptional = responsibilityRepo.findByResponsibilityIdAndUserId(responsibilityId,experienceId);
        Responsibility deletedResponsibility =null;
        if(responsibilityOptional.isPresent()){
            deletedResponsibility  =  responsibilityOptional.get();
            responsibilityRepo.delete(deletedResponsibility);
        }
        return deletedResponsibility;
    }

    @Override
    public Responsibility update(Responsibility responsibility) {
        final Optional<Responsibility>responsibilityOptional =responsibilityRepo
                .findByResponsibilityIdAndUserId(responsibility.getResponsibilityId(),responsibility.getUserId());
        Responsibility updatedresponsibility =null;
        if(responsibilityOptional.isPresent()){
            updatedresponsibility =responsibilityRepo.save(responsibility);
        }
        return updatedresponsibility;
    }
}
