package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;

public interface DeleteService {
    ResponseEntity<Response> generalDelete(long objectId, long userId, String token, long deleteType);

    ResponseEntity<Response> deleteResponsibility(long responsibilityId, long expId, long userId, String token);
}
