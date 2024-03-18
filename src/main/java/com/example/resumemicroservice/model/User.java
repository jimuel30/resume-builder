package com.example.resumemicroservice.model;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private long userId;
    private String email;

    private String password;
    private String firstName;

    private String middleName;
    private String lastName;

    private String city;

    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Social> socialList;

    private List<Resume> resumeList;
}
