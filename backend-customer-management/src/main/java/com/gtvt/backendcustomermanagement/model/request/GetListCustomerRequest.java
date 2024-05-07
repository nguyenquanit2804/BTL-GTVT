package com.gtvt.backendcustomermanagement.model.request;

import lombok.Data;

import java.sql.Date;

@Data
public class GetListCustomerRequest {
    private String idCard;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String departmentName;
    private String jobName;
}
