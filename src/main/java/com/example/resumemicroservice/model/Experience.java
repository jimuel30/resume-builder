package com.example.resumemicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "responsibilityId",fetch = FetchType.EAGER)
    private List<Responsibility> responsibilityList;

    private int  startMonth;
    private int  startYear;

    private int  entMonth;
    private int  endYear;
}
