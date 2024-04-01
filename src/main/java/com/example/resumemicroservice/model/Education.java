package com.example.resumemicroservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Table(name="educations")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long educationId;
    private String institutionName;

    private String course;

    private String startDate;
    private String  endDate;

    private long userId;

    private long resumeId;

}
