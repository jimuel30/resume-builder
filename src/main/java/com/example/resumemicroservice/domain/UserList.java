package com.example.resumemicroservice.domain;

import com.example.resumemicroservice.model.*;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserList {

    private List<Resume> resumeList;
    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Social> socialList;
    private List<Skill> skillList;
}
