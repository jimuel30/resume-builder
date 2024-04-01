package com.example.resumemicroservice.modelservice.impl;

import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.repo.ResponsibilityRepo;
import com.example.resumemicroservice.modelservice.ResponsibilityService;
import org.springframework.stereotype.Service;

@Service
public class ResponsibilityServiceImpl implements ResponsibilityService {

    private final ResponsibilityRepo responsibilityRepo;

    public ResponsibilityServiceImpl(ResponsibilityRepo responsibilityRepo) {
        this.responsibilityRepo = responsibilityRepo;
    }

    @Override
    public Responsibility save(Responsibility responsibility) {
        return responsibilityRepo.save(responsibility);
    }
}
