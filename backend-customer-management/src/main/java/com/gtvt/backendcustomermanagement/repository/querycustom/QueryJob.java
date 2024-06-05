package com.gtvt.backendcustomermanagement.repository.querycustom;

import com.gtvt.backendcustomermanagement.model.request.GetDetailByIdJobRequest;
import com.gtvt.backendcustomermanagement.model.request.GetListJobRequest;
import com.gtvt.backendcustomermanagement.model.response.GetDetailByIdJobResponse;
import com.gtvt.backendcustomermanagement.model.response.GetListJobResponse;
import com.gtvt.backendcustomermanagement.utils.SqlQueryUtil;
import com.gtvt.backendcustomermanagement.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QueryJob {
    @Autowired
    SqlQueryUtil sqlQueryUtil;

    public List<GetListJobResponse> getInfoJob(GetListJobRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("job.JOB_TITLE           AS jobName, ");
        sql.append("job.JOB_ID              AS id, ");
        sql.append("job.CREATE_DATE         AS createDate, ");
        sql.append("job.CREATE_BY           AS createBy, ");
        sql.append("job.STATUS              AS status, ");
        sql.append("count(em.DEPARTMENT_ID) AS jobCountUser ");
        sql.append("FROM ");
        sql.append("JOB job ");
        sql.append("LEFT JOIN EMPLOYEE em on job.JOB_ID = em.DEPARTMENT_ID AND (job.STATUS = 1) ");
        sql.append("GROUP BY job.JOB_TITLE, job.CREATE_DATE, job.CREATE_BY, job.STATUS, job.JOB_ID ");
        if (StringUtil.isNotNullOrEmpty(request.getJobName())) {
            sql.append("HAVING job.JOB_TITLE = :jobName ");
            params.put("jobName", request.getJobName());
        }
        return sqlQueryUtil.queryModel().queryForList(sql.toString(), params, GetListJobResponse.class);

    }

    public GetDetailByIdJobResponse getInfoJobById(GetDetailByIdJobRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("job.JOB_TITLE           AS jobName, ");
        sql.append("job.JOB_ID              AS id, ");
        sql.append("job.CREATE_DATE         AS createDate, ");
        sql.append("job.CREATE_BY           AS createBy, ");
        sql.append("job.STATUS              AS status, ");
        sql.append("count(em.DEPARTMENT_ID) AS jobCountUser ");
        sql.append("FROM ");
        sql.append("JOB job ");
        sql.append("LEFT JOIN EMPLOYEE em on job.JOB_ID = em.DEPARTMENT_ID AND (job.STATUS = 1) ");
        sql.append("GROUP BY job.JOB_TITLE, job.CREATE_DATE, job.CREATE_BY, job.STATUS, job.JOB_ID ");
        sql.append("HAVING job.JOB_ID = :id ");
        params.put("id", request.getId());
        return sqlQueryUtil.queryModel().queryForObject(sql.toString(), params, GetDetailByIdJobResponse.class);

    }


}
