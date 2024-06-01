package com.gtvt.backendcustomermanagement.repository.querycustom;

import com.gtvt.backendcustomermanagement.model.request.GetDetailByIdDepartmentRequest;
import com.gtvt.backendcustomermanagement.model.request.GetListDepartmentRequest;
import com.gtvt.backendcustomermanagement.model.response.GetDetailByIdDepartmentResponse;
import com.gtvt.backendcustomermanagement.model.response.GetListDepartmentResponse;
import com.gtvt.backendcustomermanagement.utils.SqlQueryUtil;
import com.gtvt.backendcustomermanagement.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QueryDepartment {
    @Autowired
    SqlQueryUtil sqlQueryUtil;

    public List<GetListDepartmentResponse> getInfoDepartment(GetListDepartmentRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("dp.DEPARTMENT_NAME      AS departmentName, ");
        sql.append("dp.DEPARTMENT_ID        AS id, ");
        sql.append("dp.STATUS               AS status, ");
        sql.append("dp.CREATE_BY            AS createBy, ");
        sql.append("dp.CREATE_DATE          AS createDate, ");
        sql.append("count(em.DEPARTMENT_ID) AS employeeUsedCount ");
        sql.append("FROM DEPARTMENT dp ");
        sql.append("LEFT JOIN COMPANY.EMPLOYEE em on dp.DEPARTMENT_ID = em.DEPARTMENT_ID AND (em.STATUS = 1) ");
        sql.append("GROUP BY dp.DEPARTMENT_NAME, dp.CREATE_BY, dp.STATUS, dp.CREATE_DATE, dp.DEPARTMENT_ID ");
        if (StringUtil.isNotNullOrEmpty(request.getDepartmentName())) {
            sql.append("HAVING dp.DEPARTMENT_NAME = :departmentName ");
            params.put("departmentName", request.getDepartmentName());
        }
        return sqlQueryUtil.queryModel().queryForList(sql.toString(), params, GetListDepartmentResponse.class);

    }

    public GetDetailByIdDepartmentResponse getInfoDepartmentById(GetDetailByIdDepartmentRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("dp.DEPARTMENT_NAME      AS departmentName, ");
        sql.append("dp.DEPARTMENT_ID        AS id, ");
        sql.append("dp.STATUS               AS status, ");
        sql.append("dp.CREATE_BY            AS createBy, ");
        sql.append("dp.CREATE_DATE          AS createDate, ");
        sql.append("count(em.DEPARTMENT_ID) AS employeeUsedCount ");
        sql.append("FROM DEPARTMENT dp ");
        sql.append("LEFT JOIN COMPANY.EMPLOYEE em on dp.DEPARTMENT_ID = em.DEPARTMENT_ID AND (em.STATUS = 1) ");
        sql.append("GROUP BY dp.DEPARTMENT_NAME, dp.CREATE_BY, dp.STATUS, dp.CREATE_DATE, dp.DEPARTMENT_ID ");
        sql.append("HAVING dp.DEPARTMENT_ID = :id ");
        params.put("id" , request.getDepartmentId());
        return sqlQueryUtil.queryModel().queryForObject(sql.toString(), params, GetDetailByIdDepartmentResponse.class);

    }

}
