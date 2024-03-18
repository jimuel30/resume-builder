package com.example.resumemicroservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Response {
    private int status;
    private Boolean success;
    private String message;
    private Object data;

    public void createSuccess(final Object data){
        this.status = 200;
        this.success = true;
        this.message = "SUCCESS";
        this.data = data;
    }
    public void createFail(final String message, final int status){
        this.status = status;
        this.success = false;
        this.message = message;
        this.data = null;
    }

}
