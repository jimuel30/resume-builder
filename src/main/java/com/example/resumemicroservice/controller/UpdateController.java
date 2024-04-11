package com.example.resumemicroservice.controller;


import com.example.resumemicroservice.actionservice.SaveService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
public class UpdateController {

    private final SaveService saveService;

    public UpdateController(final SaveService saveService) {
        this.saveService = saveService;
    }
}
