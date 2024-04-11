package com.example.resumemicroservice.modelservice;

import com.example.resumemicroservice.model.Skill;

import java.util.List;

public interface SkillService {
    Skill save(Skill skill);

    List<Skill> search(String skillPrefix);

    Skill delete(long skillId, long userId);

    Skill update(Skill skill);


}
