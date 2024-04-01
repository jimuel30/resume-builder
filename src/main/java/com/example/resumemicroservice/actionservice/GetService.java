package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;

public interface GetService {
    ResponseEntity<Response> getUserList(String token);
    ResponseEntity<Response>getResumeById(long resumeId,String token);

}
