package com.example.resumemicroservice.repo;


import com.example.resumemicroservice.model.Social;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialRepo  extends JpaRepository<Social, Long> {

    Optional<Social> findBySocialIdAndUserId(long socialId, long userId);

}
