package com.example.resumemicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Table(name="experiences")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long experienceId;

    private String companyName;

    private String position;

    @OneToMany(mappedBy = "experienceId",fetch = FetchType.EAGER)
    private List<Responsibility> responsibilityList;

    private String startDate;
    private String  endDate;

    private long userId;

    private long resumeId;

}
