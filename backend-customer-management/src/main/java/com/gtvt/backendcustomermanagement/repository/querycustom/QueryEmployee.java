package com.gtvt.backendcustomermanagement.repository.querycustom;

import com.gtvt.backendcustomermanagement.model.request.GetListCustomerRequest;
import com.gtvt.backendcustomermanagement.model.response.GetDetailByIdCustomerResponse;
import com.gtvt.backendcustomermanagement.model.response.GetListCustomerResponse;
import com.gtvt.backendcustomermanagement.utils.SqlQueryUtil;
import com.gtvt.backendcustomermanagement.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueryEmployee {
    @Autowired
    SqlQueryUtil sqlQueryUtil;

    public List<GetListCustomerResponse> getCustomerByInformation(GetListCustomerRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("em.EMPLOYEE_ID      AS id, ");
        sql.append("em.ID_CARD          AS idCard, ");
        sql.append("em.FIRST_NAME       AS firstName, ");
        sql.append("em.LAST_NAME        AS lastName, ");
        sql.append("em.GENDER, ");
        sql.append("em.DATE_OF_BIRTH    AS dateOfBirth,  ");
        sql.append("em.ADDRESS, ");
        sql.append("em.PHONE_NUMBER     AS phoneNumber, ");
        sql.append("em.EMAIL, ");
        sql.append("em.FACE, ");
        sql.append("dep.DEPARTMENT_NAME AS departmentName, ");
        sql.append("j.JOB_TITLE         AS jobName ");
        sql.append("FROM EMPLOYEE em ");
        sql.append("LEFT JOIN DEPARTMENT dep ON em.DEPARTMENT_ID = dep.DEPARTMENT_ID ");
        sql.append("LEFT JOIN JOB j on em.JOB_ID = j.JOB_ID ");
        sql.append("WHERE em.STATUS = 1 ");

        if (StringUtil.isNotNullOrEmpty(request.getIdCard())) {
            sql.append("AND  em.ID_CARD = :idCard ");
            params.put("idCard", request.getIdCard());
        }
        if (StringUtil.isNotNullOrEmpty(request.getLastName())) {
            sql.append("AND em.LAST_NAME = :lastName ");
            params.put("lastName", request.getLastName());
        }
        if (StringUtil.isNotNullOrEmpty(request.getGender())) {
            sql.append("AND em.GENDER = :gender ");
            params.put("gender", request.getGender());
        }
        if (StringUtil.isNotNullOrEmpty(request.getPhoneNumber())) {
            sql.append("AND em.PHONE_NUMBER = :phoneNumber ");
            params.put("phoneNumber", request.getPhoneNumber());
        }
        if (StringUtil.isNotNullOrEmpty(request.getEmail())) {
            sql.append("AND em.EMAIL = :email ");
            params.put("email", request.getEmail());
        }
        if (StringUtil.isNotNullOrEmpty(request.getDepartmentName())) {
            sql.append("AND dep.DEPARTMENT_NAME = :departmentName ");
            params.put("departmentName", request.getDepartmentName());
        }
        if (StringUtil.isNotNullOrEmpty(request.getJobName())) {
            sql.append("AND j.JOB_TITLE = :jobName ");
            params.put("jobName", request.getJobName());
        }

        return sqlQueryUtil.queryModel().queryForList(sql.toString(), params, GetListCustomerResponse.class);

    }

    public GetDetailByIdCustomerResponse getDetailByIDCustomer (Long id) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("em.EMPLOYEE_ID      AS id, ");
        sql.append("em.ID_CARD          AS idCard, ");
        sql.append("em.FIRST_NAME       AS firstName, ");
        sql.append("em.LAST_NAME        AS lastName, ");
        sql.append("em.GENDER, ");
        sql.append("em.DATE_OF_BIRTH    AS dateOfBirth,  ");
        sql.append("em.ADDRESS, ");
        sql.append("em.PHONE_NUMBER     AS phoneNumber, ");
        sql.append("em.EMAIL, ");
        sql.append("em.FACE, ");
        sql.append("dep.DEPARTMENT_NAME AS departmentName, ");
        sql.append("j.JOB_TITLE         AS jobName ");
        sql.append("FROM EMPLOYEE em ");
        sql.append("LEFT JOIN DEPARTMENT dep ON em.DEPARTMENT_ID = dep.DEPARTMENT_ID ");
        sql.append("LEFT JOIN JOB j on em.JOB_ID = j.JOB_ID ");
        sql.append("WHERE em.STATUS = 1 ");

        if (id != null) {
            sql.append("AND  em.EMPLOYEE_ID = :id ");
            params.put("id", id);
        }
        return sqlQueryUtil.queryModel().queryForObject(sql.toString(), params, GetDetailByIdCustomerResponse.class);
    }

}
