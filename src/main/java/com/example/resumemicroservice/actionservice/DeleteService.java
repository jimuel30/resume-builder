package com.example.resumemicroservice.actionservice;

import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;

public interface DeleteService {
    ResponseEntity<Response> generalDelete(long objectId, String token, long deleteType);


}
