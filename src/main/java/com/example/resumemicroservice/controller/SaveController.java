package com.example.resumemicroservice.controller;

import com.example.resumemicroservice.actionservice.SaveService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
public class SaveController {
    private final SaveService createService;

    public SaveController(final SaveService createService) {
        this.createService = createService;
    }
    @PostMapping("/skill")
    public ResponseEntity<Response> saveSkill(@RequestBody Skill skill,
                                              @RequestParam long resumeId,
                                              @RequestHeader("Authorization") String token){
        return createService.saveSkill(skill,token,resumeId);
    }

    @PostMapping("/experience")
    public ResponseEntity<Response> saveExperience(@RequestBody Experience experience,
                                                   @RequestParam long resumeId,
                                                   @RequestHeader("Authorization") String token){
        return createService.saveExperience(experience,token, resumeId);
    }
    @PostMapping("/responsibility")
    public ResponseEntity<Response> saveResponsibility(@RequestBody Responsibility responsibility,
                                                       @RequestParam long resumeId,
                                                       @RequestHeader("Authorization") String token){
        return createService.saveResponsibility(responsibility,token,resumeId);
    }

    @PostMapping("/education")
    public ResponseEntity<Response> saveEducation(@RequestBody Education education,
                                                  @RequestParam long resumeId,
                                                  @RequestHeader("Authorization") String token){
        return createService.saveEducation(education,token,resumeId);
    }

    @PostMapping("/social")
    public ResponseEntity<Response> saveSocial(@RequestBody Social social,
                                               @RequestParam long resumeId,
                                               @RequestHeader("Authorization") String token){
        return createService.saveSocial(social,token,resumeId);
    }

    @PostMapping("/resume")
    public ResponseEntity<Response> saveResume(@RequestHeader("Authorization") String token){
        return createService.saveResume(token);
    }

}
