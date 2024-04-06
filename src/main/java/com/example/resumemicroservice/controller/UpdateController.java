package com.example.resumemicroservice.controller;

import com.example.resumemicroservice.actionservice.UpdateService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/update")
public class UpdateController {
    private final UpdateService updateService;

    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }


    @PostMapping("/skill")
    public ResponseEntity<Response> saveSkill(@RequestBody Skill skill,
                                              @RequestParam long resumeId,
                                              @RequestHeader("Authorization") String token){
        return updateService.updateSkill(skill,token,resumeId);
    }

    @PostMapping("/experience")
    public ResponseEntity<Response> saveExperience(@RequestBody Experience experience,
                                                   @RequestParam long resumeId,
                                                   @RequestHeader("Authorization") String token){
        return updateService.updateExperience(experience,token, resumeId);
    }
    @PostMapping("/responsibility")
    public ResponseEntity<Response> saveResponsibility(@RequestBody Responsibility responsibility,
                                                       @RequestParam long resumeId,
                                                       @RequestHeader("Authorization") String token){
        return updateService.updateResponsibility(responsibility,token,resumeId);
    }

    @PostMapping("/education")
    public ResponseEntity<Response> saveEducation(@RequestBody Education education,
                                                  @RequestParam long resumeId,
                                                  @RequestHeader("Authorization") String token){
        return updateService.updateEducation(education,token,resumeId);
    }

    @PostMapping("/social")
    public ResponseEntity<Response> saveSocial(@RequestBody Social social,
                                               @RequestParam long resumeId,
                                               @RequestHeader("Authorization") String token){
        return updateService.updateSocial(social,token,resumeId);
    }

    @PostMapping("/resume")
    public ResponseEntity<Response> saveResume(@RequestHeader("Authorization") String token){
        return updateService.updateResume(token);
    }
}
