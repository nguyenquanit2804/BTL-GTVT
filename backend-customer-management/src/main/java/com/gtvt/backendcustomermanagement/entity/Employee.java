package com.gtvt.backendcustomermanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String idCard;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String face;
    private Long departmentId;
    private Long jobId;
    private Long salaryId;
}
