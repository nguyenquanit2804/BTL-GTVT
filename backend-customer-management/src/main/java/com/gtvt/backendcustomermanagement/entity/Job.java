package com.gtvt.backendcustomermanagement.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "JOB")
public class Job extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobTitle;
    private Double minSalary;
    private Double maxSalary;

}
