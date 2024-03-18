package com.example.resumemicroservice.util;

import com.example.resumemicroservice.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResEntityUtil {
    public static ResponseEntity<Response> success(final Object data){
        final  Response response = new Response();
        response.createSuccess(data);
        return ResponseEntity.ok(response);
    }
    public static ResponseEntity<Response> notFound(){
        final  Response response = new Response();
        response.createFail("NOT FOUND",404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    public static ResponseEntity<Response> serverError(){
        final Response response = new Response();
        response.createFail("SERVER ERROR",500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    public static ResponseEntity<Response>  badRequest(final String message){
        final Response response = new Response();
        response.createFail(message,400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
