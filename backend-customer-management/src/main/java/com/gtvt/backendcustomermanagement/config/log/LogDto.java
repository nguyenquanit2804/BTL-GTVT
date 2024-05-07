package com.gtvt.backendcustomermanagement.config.log;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LogDto {
    private Timestamp time;
    private String type;
    private Object object;
}
