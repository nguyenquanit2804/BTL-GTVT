package com.gtvt.backendcustomermanagement.model.request;

import lombok.Data;

@Data
public class DepartmentUpdateRequest {
    private Long departmentId;
    private String departmentName;
}
