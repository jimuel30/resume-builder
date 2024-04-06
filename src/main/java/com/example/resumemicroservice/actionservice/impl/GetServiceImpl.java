package com.example.resumemicroservice.actionservice.impl;

import com.example.resumemicroservice.actionservice.GetService;
import com.example.resumemicroservice.auth.JwtService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.domain.UserList;
import com.example.resumemicroservice.dto.UserDto;
import com.example.resumemicroservice.model.*;
import com.example.resumemicroservice.util.ResEntityUtil;
import com.example.resumemicroservice.util.ResumeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GetServiceImpl implements GetService {

    private final JwtService jwtService;

    public GetServiceImpl(final JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<Response> getUserList(final String token) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            List<Resume> resumeList = Objects.nonNull(user.getResumeList())?user.getResumeList():new ArrayList<>();
            List<Social> socialList = Objects.nonNull(user.getSocialList())?user.getSocialList():new ArrayList<>();
            List<Skill> skillList = Objects.nonNull(user.getSkillList())?user.getSkillList():new ArrayList<>();
            List<Education> educationList = Objects.nonNull(user.getEducationList())?user.getEducationList():new ArrayList<>();
            List<Experience> experienceList = Objects.nonNull(user.getExperienceList())?user.getExperienceList():new ArrayList<>();
            final UserList userList = UserList.builder()
                    .educationList(educationList)
                    .experienceList(experienceList)
                    .resumeList(resumeList)
                    .skillList(skillList)
                    .socialList(socialList).build();
            responseEntity = ResEntityUtil.success(userList);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Response> getResumeById(final long resumeId, final String token) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            Resume resume = ResumeUtil.resumeChecker(user,resumeId);
            if(Objects.nonNull(resume)){
                responseEntity = ResEntityUtil.success(resume);
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Response> getUserDto(final String token) {
        final User user = jwtService.extractUser(token);
        ResponseEntity<Response> responseEntity = ResEntityUtil.notFound();
        if(Objects.nonNull(user)){
            final UserDto userDto = UserDto.builder()
                    .userId(user.getUserId())
                    .middleName(user.getMiddleName())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName()).build();
            responseEntity = ResEntityUtil.success(userDto);
        }
        return responseEntity;

    }
}
