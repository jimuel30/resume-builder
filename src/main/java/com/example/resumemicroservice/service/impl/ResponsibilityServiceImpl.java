package com.example.resumemicroservice.service.impl;

import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.repo.ResponsibilityRepo;
import com.example.resumemicroservice.service.ResponsibilityService;
import org.springframework.stereotype.Service;

@Service
public class ResponsibilityServiceImpl implements ResponsibilityService {

    private final ResponsibilityRepo responsibilityRepo;

    public ResponsibilityServiceImpl(ResponsibilityRepo responsibilityRepo) {
        this.responsibilityRepo = responsibilityRepo;
    }

    @Override
    public Responsibility createResponsibility(Responsibility responsibility) {
        return responsibilityRepo.save(responsibility);
    }
}
