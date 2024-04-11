package com.example.resumemicroservice.actionservice.impl;

import com.example.resumemicroservice.actionservice.SaveService;
import com.example.resumemicroservice.auth.JwtService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.*;
import com.example.resumemicroservice.repo.UserRepo;
import com.example.resumemicroservice.modelservice.*;
import com.example.resumemicroservice.util.ResEntityUtil;
import com.example.resumemicroservice.util.ResumeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SaveServiceImpl implements SaveService {

    private final SkillService skillService;
    private final JwtService jwtService;
    private final ExperienceService experienceService;
    private final ResponsibilityService responsibilityService;

    private final EducationService educationService;

    private final SocialService socialService;

    private final ResumeService resumeService;

    private final UserRepo userRepo;

    public SaveServiceImpl(final SkillService skillService,
                           final JwtService jwtService,
                           final ExperienceService experienceService,
                           final ResponsibilityService responsibilityService,
                           final EducationService educationService,
                           final SocialService socialService,
                           final ResumeService resumeService,
                           final UserRepo userRepo) {
        this.skillService = skillService;
        this.jwtService = jwtService;
        this.experienceService = experienceService;
        this.responsibilityService = responsibilityService;
        this.educationService = educationService;
        this.socialService = socialService;
        this.resumeService = resumeService;
        this.userRepo = userRepo;
    }


    @Override
    public ResponseEntity<Response> saveSkill(final Skill skill,
                                              final String token,
                                              final long resumeId) {

        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){

            final Resume myResume = ResumeUtil.resumeChecker(user,resumeId);
            final boolean myResumeNonNull = Objects.nonNull(myResume);
            if(myResumeNonNull){
                skill.setResumeId(resumeId);
            }
            skill.setUserId(user.getUserId());
            final Skill savedSkill = skillService.save(skill);
            if(Objects.nonNull(savedSkill)){
                final List<Skill> skillList = Objects.nonNull(user.getSkillList())?user.getSkillList():new ArrayList<>();
                skillList.add(savedSkill);
                user.setSkillList(skillList);
                userRepo.save(user);
                responseEntity = ResEntityUtil.success(savedSkill);


                if(myResumeNonNull){
                    final List<Skill> resumeSkillList = Objects.nonNull(myResume.getSkillList())
                            ?user.getSkillList()
                            :new ArrayList<>();
                    resumeSkillList.add(savedSkill);
                    myResume.setSkillList(resumeSkillList);
                    resumeService.save(myResume);
                }
            }
        }
        return  responseEntity;
    }

    @Override
    public ResponseEntity<Response> saveExperience(final Experience experience,
                                                   final String token,
                                                   final long resumeId) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            experience.setUserId(user.getUserId());
            final Resume myResume = ResumeUtil.resumeChecker(user,resumeId);
            final boolean myResumeNonNull = Objects.nonNull(myResume);
            if(myResumeNonNull){
                experience.setResumeId(resumeId);
            }
            final Experience saveExp = experienceService.save(experience);
            final List<Experience> expList = Objects.nonNull(user.getSkillList())?user.getExperienceList():new ArrayList<>();
            expList.add(saveExp);
            user.setExperienceList(expList);
            userRepo.save(user);
            responseEntity = ResEntityUtil.success(saveExp);


            if(myResumeNonNull){
                final List<Experience> resumeExpList = Objects.nonNull(myResume.getSkillList())
                        ?user.getExperienceList()
                        :new ArrayList<>();

                resumeExpList.add(saveExp);
                myResume.setExperienceList(resumeExpList);
                resumeService.save(myResume);
            }
        }
        return  responseEntity;
    }

    @Override
    public ResponseEntity<Response> saveEducation(final Education education,
                                                  final String token,
                                                  final long resumeId) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            final Resume myResume = ResumeUtil.resumeChecker(user,resumeId);
            final boolean myResumeNonNull = Objects.nonNull(myResume);
            education.setUserId(user.getUserId());
            if(myResumeNonNull){
                education.setResumeId(resumeId);
            }
            final Education savedEducation = educationService.save(education);
            final List<Education> educationList = Objects.nonNull(user.getEducationList())?user.getEducationList():new ArrayList<>();
            educationList.add(savedEducation);
            user.setEducationList(educationList);
            userRepo.save(user);
            responseEntity = ResEntityUtil.success(savedEducation);
            if(myResumeNonNull){
                final List<Education> resumeEducList = Objects.nonNull(myResume.getEducationList())
                        ?user.getEducationList()
                        :new ArrayList<>();
                resumeEducList.add(savedEducation);
                myResume.setEducationList(resumeEducList);
                resumeService.save(myResume);
            }
        }
        return  responseEntity;
    }


    @Override
    public ResponseEntity<Response> saveResponsibility(final Responsibility responsibility,
                                                       final String token,
                                                       final long resumeId) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            final List<Experience> experienceList = user.getExperienceList();
            if(Objects.nonNull(experienceList)){
                for (Experience experience:experienceList) {
                    if(experience.getExperienceId()==responsibility.getExperienceId()){
                        responsibility.setUserId(user.getUserId());
                        final  Responsibility savedResponsibility = responsibilityService.save(responsibility);
                        final  List<Responsibility> responsibilityList = Objects.nonNull(experience.getResponsibilityList())?
                                                                            experience.getResponsibilityList():new ArrayList<>();
                        responsibilityList.add(savedResponsibility);
                        experience.setResponsibilityList(responsibilityList);
                        //final Experience savedExp = experienceService.save(experience);
                        responseEntity = ResEntityUtil.success(savedResponsibility);
                        final Resume myResume = ResumeUtil.resumeChecker(user,resumeId);
                        if(Objects.nonNull(myResume)){
                            final List<Experience> resumeExpList = Objects.nonNull(myResume.getSkillList())
                                    ?user.getExperienceList()
                                    :new ArrayList<>();
                            boolean expInResume = false;
                            int expIndex = 0;
                            final Experience savedExp = experienceService.getExperienceById(responsibility.getExperienceId());
                            for (Experience exp:resumeExpList) {
                                 expInResume = exp.equals(savedExp);
                                 if(expInResume){
                                     resumeExpList.get(expIndex).getResponsibilityList().add(savedResponsibility);
                                     break;
                                 }
                                 expIndex++;
                            }
                            if(!expInResume){
                                resumeExpList.add(savedExp);
                            }
                            myResume.setExperienceList(resumeExpList);
                            resumeService.save(myResume);
                        }
                        break;
                    }
                }
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Response> saveSocial(final Social social, final String token, final long resumeId) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
           final List<Social> socialList = Objects.nonNull(user.getSocialList())?user.getSocialList():new ArrayList<>();
            final Resume myResume = ResumeUtil.resumeChecker(user,resumeId);
            final boolean myResumeNonNull = Objects.nonNull(myResume);
           boolean alreadySave= false;
            for (Social mySocial: socialList) {
                alreadySave= mySocial.getSocialLink().equals(social.getSocialLink());
                if(alreadySave){
                    responseEntity = ResEntityUtil.badRequest("Already saved!");
                    break;
                }
            }
           if(!alreadySave){
               if(myResumeNonNull){
                   social.setResumeId(resumeId);
               }
               social.setUserId(user.getUserId());
               final Social savedSocial = socialService.save(social);

              if(Objects.nonNull(savedSocial)){
                  socialList.add(savedSocial);
                  user.setSocialList(socialList);
                  userRepo.save(user);
                  responseEntity = ResEntityUtil.success(savedSocial);


                  if(myResumeNonNull){
                      final List<Social> resumeSocialList = Objects.nonNull(myResume.getEducationList())
                              ?user.getSocialList()
                              :new ArrayList<>();
                      resumeSocialList.add(savedSocial);
                      myResume.setSocialList(resumeSocialList);
                      resumeService.save(myResume);
                  }
              }

           }
        }
        return  responseEntity;
    }

    @Override
    public ResponseEntity<Response> saveResume(final String token) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            Resume savedResume = new Resume();
            savedResume.setUserId(user.getUserId());
            savedResume.setCity(user.getCity());
            savedResume.setFirstName(user.getFirstName());
            savedResume.setMiddleName(user.getMiddleName());
            savedResume.setLastName(user.getLastName());
            savedResume.setEmail(user.getEmail());
            savedResume.setProfession(user.getProfession());
            savedResume = resumeService.save(savedResume);
            savedResume.setSkillList(new ArrayList<>());
            savedResume.setEducationList(new ArrayList<>());
            savedResume.setExperienceList(new ArrayList<>());
            savedResume.setSocialList(new ArrayList<>());
            responseEntity = ResEntityUtil.success(savedResume);
        }
        return responseEntity;
    }





}
