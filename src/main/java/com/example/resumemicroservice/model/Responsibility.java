package com.example.resumemicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name="responsibilities")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Responsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long responsibilityId;
    private String responsibility;
    private long experienceId;

    private long userId;
}
