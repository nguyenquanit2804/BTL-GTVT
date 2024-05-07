package com.gtvt.backendcustomermanagement.repository.querycustom;

import com.gtvt.backendcustomermanagement.model.request.GetListDepartmentRequest;
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
        sql.append("count(em.DEPARTMENT_ID) AS employeeUsedCount ");
        sql.append("FROM DEPARTMENT dp ");
        sql.append("JOIN COMPANY.EMPLOYEE em on dp.DEPARTMENT_ID = em.DEPARTMENT_ID AND (dp.STATUS = 1) ");
        sql.append("GROUP BY dp.DEPARTMENT_NAME ");
        if (StringUtil.isNotNullOrEmpty(request.getDepartmentName())) {
            sql.append("HAVING dp.DEPARTMENT_NAME = :departmentName ");
            params.put("departmentName", request.getDepartmentName());
        }
        return sqlQueryUtil.queryModel().queryForList(sql.toString(), params, GetListDepartmentResponse.class);

    }

}
