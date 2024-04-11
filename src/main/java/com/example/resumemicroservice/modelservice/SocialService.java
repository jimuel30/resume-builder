package com.example.resumemicroservice.modelservice;

import com.example.resumemicroservice.model.Social;

public interface SocialService {
    Social save(Social social);
    Social delete(long socialId, long userId);


    Social update(Social social);
}
