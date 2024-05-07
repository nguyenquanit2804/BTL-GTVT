package com.gtvt.backendcustomermanagement.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
public class EmployeeDeleteRequest {
    private Long id;
}
