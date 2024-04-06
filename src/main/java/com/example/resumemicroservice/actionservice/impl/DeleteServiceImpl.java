package com.example.resumemicroservice.actionservice.impl;

import com.example.resumemicroservice.actionservice.DeleteService;
import com.example.resumemicroservice.auth.JwtService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.model.User;
import com.example.resumemicroservice.modelservice.*;
import com.example.resumemicroservice.util.ResEntityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class DeleteServiceImpl implements DeleteService {

    private final JwtService jwtService;
    private final EducationService educationService;

    private final SkillService skillService;

    private  final SocialService socialService;

    private final ResumeService resumeService;

    private final ExperienceService experienceService;

    public DeleteServiceImpl(final JwtService jwtService,
                             final EducationService educationService,
                             final SkillService skillService,
                             final SocialService socialService,
                             final ResumeService resumeService,
                             final ExperienceService experienceService) {
        this.jwtService = jwtService;
        this.educationService = educationService;
        this.skillService = skillService;
        this.socialService = socialService;
        this.resumeService = resumeService;
        this.experienceService = experienceService;
    }

    @Override
    public ResponseEntity<Response> generalDelete(final long objectId,
                                                  final String token,
                                                  final long deleteType) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            Object deletedObject;
            //0 = EDUCATION
            //1 = SKILL
            //2 =SOCIAL
            //3 = RESUME
            //4 = EXPERIENCE
            //5 = RESPONSIBILITY
            if(0 == deleteType){
                deletedObject  = educationService.delete(objectId,user.getUserId());
            } else if (1 == deleteType) {
                deletedObject = skillService.delete(objectId,user.getUserId());
            }
            else if (2 == deleteType) {
               deletedObject = socialService.delete(objectId,user.getUserId());
            }
            else if (3 == deleteType) {
                deletedObject = resumeService.delete(objectId,user.getUserId());
            }
            else{
                deletedObject = experienceService.delete(objectId,user.getUserId());
            }
            if(Objects.nonNull(deletedObject)){
                responseEntity = ResEntityUtil.success(deletedObject);
            }
        }
        return  responseEntity;
    }

    @Override
    public ResponseEntity<Response> deleteResponsibility(final long responsibilityId,
                                                         final long expId,
                                                         final String token) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            Responsibility deletedResponsibility = experienceService.deleteResponsibility(expId,responsibilityId,user.getUserId());
            if(Objects.nonNull(deletedResponsibility)){
                responseEntity = ResEntityUtil.success(deletedResponsibility);
            }
        }
        return  responseEntity;
    }
}
