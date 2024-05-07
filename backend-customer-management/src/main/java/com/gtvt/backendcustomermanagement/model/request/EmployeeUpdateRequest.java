package com.gtvt.backendcustomermanagement.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
public class EmployeeUpdateRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private MultipartFile face;
}
