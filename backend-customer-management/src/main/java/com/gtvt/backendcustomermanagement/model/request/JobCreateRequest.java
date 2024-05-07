package com.gtvt.backendcustomermanagement.model.request;


import lombok.Data;

@Data
public class JobCreateRequest {
    private String jobName;
    private Double minSalary;
    private Double maxSalary;
}
