package com.example.resumemicroservice.actionservice.impl;

import com.example.resumemicroservice.actionservice.CreateService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.Skill;
import com.example.resumemicroservice.service.SkillService;
import com.example.resumemicroservice.util.ResEntityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateServiceImpl implements CreateService {

    private final SkillService skillService;

    public CreateServiceImpl(final SkillService skillService) {
        this.skillService = skillService;
    }


    @Override
    public ResponseEntity<Response> createSkill(Skill skill, String token) {
        //todo get user from token
        ResponseEntity<Response> responseEntity = ResEntityUtil.badRequest("Something went wrong");
        final Skill savedSkill = skillService.createSkill(skill);
        responseEntity = ResEntityUtil.success(savedSkill);
        return  responseEntity;
    }
}
