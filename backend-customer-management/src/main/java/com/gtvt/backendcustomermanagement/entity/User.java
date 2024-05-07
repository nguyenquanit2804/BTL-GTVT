package com.gtvt.backendcustomermanagement.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;


@Data
@Entity
@Table(name = "USR")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = AUTO, generator = "USR_SEQ")
    @SequenceGenerator(name = "USR_SEQ", sequenceName = "USR_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USR_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCIPTION")
    private String desciption;

}
