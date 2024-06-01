package com.gtvt.backendcustomermanagement.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class GetDetailByIdDepartmentResponse {
    private Long id;
    private Long employeeUsedCount;
    private String departmentName;
    private Long status;
    private Date createDate;
    private String createBy;
}
