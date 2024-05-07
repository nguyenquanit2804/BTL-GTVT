package com.gtvt.backendcustomermanagement.services;


import com.gtvt.backendcustomermanagement.common.CustomerManagement;
import com.gtvt.backendcustomermanagement.entity.Department;
import com.gtvt.backendcustomermanagement.model.request.DepartmentCreateRequest;
import com.gtvt.backendcustomermanagement.model.request.DepartmentDeleteRequest;
import com.gtvt.backendcustomermanagement.model.request.DepartmentUpdateRequest;
import com.gtvt.backendcustomermanagement.model.request.GetListDepartmentRequest;
import com.gtvt.backendcustomermanagement.model.response.GetListDepartmentResponse;
import com.gtvt.backendcustomermanagement.repository.DepartmentRepository;
import com.gtvt.backendcustomermanagement.repository.querycustom.QueryDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {


    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    QueryDepartment queryDepartment;

    @Autowired
    EmployeeService employeeService;


    public void addDepartment(DepartmentCreateRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("Info Department null");
        }
        Department findDepartment = departmentRepository.findFirstByDepartmentName(request.getDepartmentName());
        if (findDepartment != null ) {
            throw new IllegalStateException("Department exist");
        }
        Department department = new Department();
        department.setDepartmentName(request.getDepartmentName());
        departmentRepository.save(department);
    }

    public void updateDepartment(DepartmentUpdateRequest request) {
        Department findDepartment = departmentRepository.findFirstByDepartmentId(request.getDepartmentId());
        if (findDepartment == null ) {
            throw new IllegalStateException("Department doesn't exist");
        }
        Boolean checkDepartment = employeeService.getCustomerByDepartmentId(request.getDepartmentId());
        if (!checkDepartment) {
            throw new IllegalStateException("Department is being used");
        }
        findDepartment.setDepartmentName(request.getDepartmentName());
        departmentRepository.save(findDepartment);
    }

    public void deleteDepartment(DepartmentDeleteRequest request) {
        Department findDepartment = departmentRepository.findFirstByDepartmentId(request.getDepartmentId());
        if (findDepartment == null ) {
            throw new IllegalStateException("Department doesn't exist");
        }
        Boolean checkDepartment = employeeService.getCustomerByDepartmentId(request.getDepartmentId());
        if (!checkDepartment) {
            throw new IllegalStateException("Department is being used");
        }
        findDepartment.setStatus(CustomerManagement.CLOSED);
        departmentRepository.save(findDepartment);
    }

    public List<GetListDepartmentResponse> getListDepartment (GetListDepartmentRequest request) {
        return queryDepartment.getInfoDepartment(request);

    }


}
