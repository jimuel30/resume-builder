package com.example.resumemicroservice.actionservice.impl;

import com.example.resumemicroservice.actionservice.SearchService;
import com.example.resumemicroservice.domain.Response;
import com.example.resumemicroservice.modelservice.SkillService;
import com.example.resumemicroservice.util.ResEntityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    private final SkillService skillService;

    public SearchServiceImpl(final SkillService skillService) {
        this.skillService = skillService;
    }

    @Override
    public ResponseEntity<Response> searchSkillContainingPrefix(final String skillPrefix) {
        return ResEntityUtil.success(skillService.search(skillPrefix));
    }

}
