package com.example.resumemicroservice.controller;

import com.example.resumemicroservice.actionservice.GetService;
import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/get")
public class GetController {
    private final GetService getService;

    public GetController(final GetService getService) {
        this.getService = getService;
    }

    @GetMapping("/userList")
    public ResponseEntity<Response> getUserList(@RequestHeader("Authorization") String token){
        return getService.getUserList(token);
    }

    @GetMapping("/resume/byId")
    public ResponseEntity<Response> getResumeById(@RequestParam long resumeId,
                                                  @RequestHeader("Authorization") String token){
        return getService.getResumeById(resumeId,token);
    }
}
