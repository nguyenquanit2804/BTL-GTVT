package com.gtvt.backendcustomermanagement.model.response;

import lombok.Data;

import java.sql.Date;

@Data
public class GetDetailByIdCustomerResponse {
    private Long id;
    private String idCard;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String face;
    private String departmentName;
    private String jobName;
}
