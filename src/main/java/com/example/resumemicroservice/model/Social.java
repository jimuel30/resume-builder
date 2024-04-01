package com.example.resumemicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name="socials")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Social{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long socialId;
    private String socialLink;

    private String socialName;

    private long userId;

    private long resumeId;

}
