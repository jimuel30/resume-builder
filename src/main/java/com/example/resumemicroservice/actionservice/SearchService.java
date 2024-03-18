package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;

public interface SearchService {
    ResponseEntity<Response> searchSkillContainingPrefix(String skillPrefix);
}
