package com.example.resumemicroservice.service.impl;

import com.example.resumemicroservice.model.Skill;
import com.example.resumemicroservice.repo.SkillRepo;
import com.example.resumemicroservice.service.SkillService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepo skillRepo;

    public SkillServiceImpl(final SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }

    @Override
    public Skill createSkill(@NonNull Skill skill) {
        return skillRepo.save(skill);
    }

    @Override
    public List<Skill> searchSkill(String skillPrefix) {
        return skillRepo.findBySkillContainingIgnoreCase(skillPrefix);
    }


}
