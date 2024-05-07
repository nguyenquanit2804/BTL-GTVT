package com.gtvt.backendcustomermanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "SALARY")
public class Salary extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    private Double salaryAmount;
    private Date startDate;
    private Date endDate;
}
