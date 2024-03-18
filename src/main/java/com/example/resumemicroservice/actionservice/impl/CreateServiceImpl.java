package com.example.resumemicroservice.actionservice.impl;

import com.example.resumemicroservice.actionservice.CreateService;
import com.example.resumemicroservice.auth.JwtService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.Experience;
import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.model.Skill;
import com.example.resumemicroservice.model.User;
import com.example.resumemicroservice.repo.UserRepo;
import com.example.resumemicroservice.service.ExperienceService;
import com.example.resumemicroservice.service.ResponsibilityService;
import com.example.resumemicroservice.service.SkillService;
import com.example.resumemicroservice.util.ResEntityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CreateServiceImpl implements CreateService {

    private final SkillService skillService;
    private final JwtService jwtService;
    private final ExperienceService experienceService;
    private final ResponsibilityService responsibilityService;

    private final UserRepo userRepo;

    public CreateServiceImpl(final SkillService skillService,
                             final JwtService jwtService,
                             final ExperienceService experienceService,
                             final ResponsibilityService responsibilityService,
                             final UserRepo userRepo) {
        this.skillService = skillService;
        this.jwtService = jwtService;
        this.experienceService = experienceService;
        this.responsibilityService = responsibilityService;
        this.userRepo = userRepo;
    }


    @Override
    public ResponseEntity<Response> createSkill(final Skill skill, final String token) {

        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            final Skill savedSkill = skillService.createSkill(skill);
            final List<Skill> skillList = Objects.nonNull(user.getSkillList())?user.getSkillList():new ArrayList<>();
            skillList.add(savedSkill);
            user.setSkillList(skillList);
            userRepo.save(user);
            responseEntity = ResEntityUtil.success(savedSkill);
        }
        return  responseEntity;
    }

    @Override
    public ResponseEntity<Response> createExperience(Experience experience, String token) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            final Experience saveExp = experienceService.createExperience(experience);
            final List<Experience> expList = Objects.nonNull(user.getSkillList())?user.getExperienceList():new ArrayList<>();
            expList.add(saveExp);
            user.setExperienceList(expList);
            userRepo.save(user);
            responseEntity = ResEntityUtil.success(saveExp);
        }
        return  responseEntity;
    }

    @Override
    public ResponseEntity<Response> createResponsibility(Responsibility responsibility, long experienceId, String token) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            final List<Experience> experienceList = user.getExperienceList();
            if(Objects.nonNull(experienceList)){
                for (Experience experience:experienceList) {
                    if(experience.getExperienceId()==experienceId){
                        final  Responsibility savedResponsibility = responsibilityService.createResponsibility(responsibility);
                        final  List<Responsibility> responsibilityList = Objects.nonNull(experience.getResponsibilityList())?
                                                                            experience.getResponsibilityList():new ArrayList<>();
                        responsibilityList.add(savedResponsibility);
                        experience.setResponsibilityList(responsibilityList);
                        experienceService.createExperience(experience);
                        responseEntity = ResEntityUtil.success(savedResponsibility);
                        break;
                    }
                }
            }
        }
        return responseEntity;
    }
}
