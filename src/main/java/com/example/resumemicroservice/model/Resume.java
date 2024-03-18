package com.example.resumemicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Table(name="resumes")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resume{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resumeId;
    private String email;

    private String password;

    private String firstName;

    private String middleName;
    private String lastName;

    private String profession;

    private String city;

    @OneToMany(mappedBy = "educationId",fetch = FetchType.EAGER)
    private List<Education> educationList;

    @OneToMany(mappedBy = "experienceId",fetch = FetchType.EAGER)
    private List<Experience> experienceList;
    @OneToMany(mappedBy = "socialId",fetch = FetchType.EAGER)
    private List<Social> socialList;

    @OneToMany(mappedBy = "skillId",fetch = FetchType.EAGER)
    private List<Skill> skillList;


}
