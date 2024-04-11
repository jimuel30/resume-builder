package com.example.resumemicroservice.modelservice.impl;

import com.example.resumemicroservice.model.Education;
import com.example.resumemicroservice.model.Skill;
import com.example.resumemicroservice.repo.SkillRepo;
import com.example.resumemicroservice.modelservice.SkillService;
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
    public Skill save(final Skill skill) {
        final  Optional<Skill> optionalSkill = skillRepo.findBySkillNameIgnoreCase(skill.getSkillName());

        Skill savedSkill;
        if(optionalSkill.isPresent()){
            savedSkill = update(optionalSkill.get());
        }
        else{
            savedSkill = skillRepo.save(skill);
        }

        return savedSkill;
    }

    @Override
    public List<Skill> search(String skillPrefix) {
        return skillRepo.findBySkillNameContainingIgnoreCase(skillPrefix);
    }

    @Override
    public Skill delete(long skillId, long userId) {
        final Optional<Skill> skillOptional = skillRepo.findBySkillIdAndUserId(skillId,userId);
        Skill deletedSkill =null;
        if(skillOptional.isPresent()){
            deletedSkill =  skillOptional.get();
            skillRepo.delete(deletedSkill);
        }
        return deletedSkill;
    }

    @Override
    public Skill update(final Skill skill) {
        final Optional<Skill> skillOptional = skillRepo.findBySkillIdAndUserId(skill.getSkillId(),skill.getUserId());
        Skill updatedSkill =null;
        if(skillOptional.isPresent()){
            updatedSkill = skillRepo.save(skill);
        }
        return updatedSkill;
    }


}
