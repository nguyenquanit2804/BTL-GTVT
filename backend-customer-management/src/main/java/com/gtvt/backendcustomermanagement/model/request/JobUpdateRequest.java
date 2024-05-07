package com.gtvt.backendcustomermanagement.model.request;


import lombok.Data;

@Data
public class JobUpdateRequest {
    private Long idJob;
    private String jobName;
    private Double minSalary;
    private Double maxSalary;
}
