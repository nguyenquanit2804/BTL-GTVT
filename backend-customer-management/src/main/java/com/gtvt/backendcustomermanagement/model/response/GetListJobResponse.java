package com.gtvt.backendcustomermanagement.model.response;

import lombok.Data;

@Data
public class GetListJobResponse {
    private Long id;
    private String jobName;
    private String createDate;
    private String createBy;
    private Long status;
    private String jobCountUser;
}
