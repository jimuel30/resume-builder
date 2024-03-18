package com.example.resumemicroservice.controller;

import com.example.resumemicroservice.actionservice.CreateService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.model.Experience;
import com.example.resumemicroservice.model.Responsibility;
import com.example.resumemicroservice.model.Skill;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create")
public class CreateController {
    private final CreateService createService;

    public CreateController(final CreateService createService) {
        this.createService = createService;
    }
    @PostMapping("/skill")
    public ResponseEntity<Response> createSkill(@RequestBody Skill skill,
                                                @RequestHeader("Authorization") String token){
        return createService.createSkill(skill,token);
    }

    @PostMapping("/experience")
    public ResponseEntity<Response> createExperience(@RequestBody Experience experience,
                                                     @RequestHeader("Authorization") String token){
        return createService.createExperience(experience,token);
    }
    @PostMapping("/responsibility")
    public ResponseEntity<Response> createResponsiblity(@RequestBody Responsibility responsibility,
                                                        @RequestParam long experienceId,
                                                        @RequestHeader("Authorization") String token){
        return createService.createResponsibility(responsibility,experienceId,token);
    }

}
