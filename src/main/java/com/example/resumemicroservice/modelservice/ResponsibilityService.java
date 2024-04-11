package com.example.resumemicroservice.modelservice;

import com.example.resumemicroservice.model.Responsibility;

public interface ResponsibilityService {
    Responsibility save(Responsibility responsibility);

    Responsibility delete(long responsibilityId, long experienceId);



    Responsibility update(Responsibility responsibility);


}
