package com.gtvt.backendcustomermanagement.model.response;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Date;

@Data
public class GetListDepartmentResponse {
    private Long id;
    private Long employeeUsedCount;
    private String departmentName;
    private Long status;
    private Date createDate;
    private String createBy;
}
