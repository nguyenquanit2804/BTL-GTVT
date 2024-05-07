package com.gtvt.backendcustomermanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    private String departmentName;
    private Long entityId;
}
