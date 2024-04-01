package com.example.resumemicroservice.controller;


import com.example.resumemicroservice.actionservice.DeleteService;
import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete")
public class DeleteController {
    private final DeleteService deleteService;

    public DeleteController(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    @DeleteMapping("/education")
    public ResponseEntity<Response> deleteEducation(@RequestParam long educationId,
                                                    @RequestParam long userId,
                                                    @RequestHeader("Authorization") String token){
            return  deleteService.generalDelete(educationId,userId,token,0);
    }

    @DeleteMapping("/skill")
    public ResponseEntity<Response> deleteSkill(@RequestParam long skillId,
                                                @RequestParam long userId,
                                                @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(skillId,userId,token,1);
    }

    @DeleteMapping("/social")
    public ResponseEntity<Response> deleteSocial(@RequestParam long socialId,
                                                 @RequestParam long userId,
                                                 @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(socialId,userId,token,2);
    }

    @DeleteMapping("/resume")
    public ResponseEntity<Response> deleteResume(@RequestParam long resumeId,
                                                 @RequestParam long userId,
                                                 @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(resumeId,userId,token,3);
    }

    @DeleteMapping("/experience")
    public ResponseEntity<Response> deleteExperience(@RequestParam long experienceId,
                                                     @RequestParam long userId,
                                                     @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(experienceId,userId,token,4);
    }
    @DeleteMapping("/responsibility")
    public ResponseEntity<Response> deleteResponsibility(@RequestParam long experienceId,
                                                         @RequestParam long responsibilityId,
                                                         @RequestParam long userId,
                                                         @RequestHeader("Authorization") String token){
        return  deleteService.deleteResponsibility(responsibilityId,experienceId,userId,token);
    }

}
