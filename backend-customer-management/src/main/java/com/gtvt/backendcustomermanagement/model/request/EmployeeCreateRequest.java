package com.gtvt.backendcustomermanagement.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
public class EmployeeCreateRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String idCard;
    private Long departmentId;
    private Long jobId;
    private MultipartFile face;
}
