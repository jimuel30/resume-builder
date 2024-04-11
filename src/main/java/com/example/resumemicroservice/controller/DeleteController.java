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
                                                    @RequestHeader("Authorization") String token){
            return  deleteService.generalDelete(educationId,token,0);
    }

    @DeleteMapping("/skill")
    public ResponseEntity<Response> deleteSkill(@RequestParam long skillId,
                                                @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(skillId,token,1);
    }

    @DeleteMapping("/social")
    public ResponseEntity<Response> deleteSocial(@RequestParam long socialId,
                                                 @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(socialId,token,2);
    }

    @DeleteMapping("/resume")
    public ResponseEntity<Response> deleteResume(@RequestParam long resumeId,
                                                 @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(resumeId,token,3);
    }

    @DeleteMapping("/experience")
    public ResponseEntity<Response> deleteExperience(@RequestParam long experienceId,

                                                     @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(experienceId,token,4);
    }
    @DeleteMapping("/responsibility")
    public ResponseEntity<Response> deleteResponsibility(@RequestParam long experienceId,
                                                         @RequestParam long responsibilityId,
                                                         @RequestHeader("Authorization") String token){
        return  deleteService.generalDelete(experienceId,token,5);
    }

}
