package com.example.resumemicroservice.controller;

import com.example.resumemicroservice.actionservice.SearchService;
import com.example.resumemicroservice.domain.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(final SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/skill-prefix")
    public ResponseEntity<Response> searchSkillContainingPrefix(@RequestParam String prefix){
        return searchService.searchSkillContainingPrefix(prefix);
    }


}
