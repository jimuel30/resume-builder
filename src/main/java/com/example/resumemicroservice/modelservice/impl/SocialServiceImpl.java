package com.example.resumemicroservice.modelservice.impl;


import com.example.resumemicroservice.model.Social;
import com.example.resumemicroservice.repo.SocialRepo;
import com.example.resumemicroservice.modelservice.SocialService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SocialServiceImpl implements SocialService {

    private final SocialRepo socialRepo;

    public SocialServiceImpl(final SocialRepo socialRepo) {
        this.socialRepo = socialRepo;
    }

    @Override
    public Social save(final Social social) {
        Social savedSocial = update(social);
        if(!Objects.nonNull(savedSocial)){
            savedSocial = socialRepo.save(social);
        }
        return savedSocial;
    }

    @Override
    public Social delete(final long socialId, final long userId) {
        final Optional<Social> socialOptional = socialRepo.findBySocialIdAndUserId(socialId,userId);
        Social deletedSocial =null;
        if(socialOptional.isPresent()){
            deletedSocial =  socialOptional.get();
            socialRepo.delete(deletedSocial);
        }
        return deletedSocial;
    }

    @Override
    public Social update(Social social) {
        final Optional<Social> socialOptional = socialRepo
                .findBySocialIdAndUserId( social.getSocialId(), social.getUserId());
        Social updatedSocial =null;
        if(socialOptional.isPresent()){
            updatedSocial = socialRepo.save(social);
        }
        return updatedSocial;
    }
}
