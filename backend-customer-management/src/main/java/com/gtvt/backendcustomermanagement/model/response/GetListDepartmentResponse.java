package com.gtvt.backendcustomermanagement.model.response;

import lombok.Data;

import java.sql.Date;

@Data
public class GetListDepartmentResponse {

    private Long employeeUsedCount;
    private String departmentName;
}
