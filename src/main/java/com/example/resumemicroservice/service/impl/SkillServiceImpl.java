package com.example.resumemicroservice.service.impl;

import com.example.resumemicroservice.model.Skill;
import com.example.resumemicroservice.repo.SkillRepo;
import com.example.resumemicroservice.service.SkillService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepo skillRepo;

    public SkillServiceImpl(final SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }

    @Override
    public Skill createSkill(final Skill skill) {
        final  Optional<Skill> optionalSkill = skillRepo.findBySkillNameIgnoreCase(skill.getSkillName());
        return optionalSkill.orElseGet(() -> skillRepo.save(skill));
    }

    @Override
    public List<Skill> searchSkill(String skillPrefix) {
        return skillRepo.findBySkillNameContainingIgnoreCase(skillPrefix);
    }


}
