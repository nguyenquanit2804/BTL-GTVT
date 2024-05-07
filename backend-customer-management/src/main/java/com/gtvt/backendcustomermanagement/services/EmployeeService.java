package com.gtvt.backendcustomermanagement.services;

import cn.hutool.core.lang.UUID;
import com.gtvt.backendcustomermanagement.common.CustomerManagement;
import com.gtvt.backendcustomermanagement.entity.Employee;
import com.gtvt.backendcustomermanagement.minio.MinioCompany;
import com.gtvt.backendcustomermanagement.model.request.EmployeeCreateRequest;
import com.gtvt.backendcustomermanagement.model.request.EmployeeDeleteRequest;
import com.gtvt.backendcustomermanagement.model.request.EmployeeUpdateRequest;
import com.gtvt.backendcustomermanagement.model.request.GetListCustomerRequest;
import com.gtvt.backendcustomermanagement.model.response.GetListCustomerResponse;
import com.gtvt.backendcustomermanagement.repository.EmployeeRepository;
import com.gtvt.backendcustomermanagement.repository.querycustom.QueryEmployee;
import com.gtvt.backendcustomermanagement.utils.DateUtil;
import com.gtvt.backendcustomermanagement.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    QueryEmployee queryEmployee;

    @Autowired
    MinioCompany minioCompany;

    public void addEmployee(EmployeeCreateRequest request) {
        String nameFace = UUID.randomUUID().toString();
//        kiem tra request null
        if (request == null) {
            throw new IllegalArgumentException("Request is Null");
        }
        if (StringUtil.isNullOrEmpty(request.getLastName())) {
            throw new IllegalArgumentException("Name is Null");
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(request, employee);

        String uploadImageMinio = minioCompany.putObject(request.getFace(), "/employee/" + DateUtil.getDateToday() + "/" + nameFace);
        employee.setFace(uploadImageMinio);
        employee.setStatus(CustomerManagement.OPEN);
        employeeRepository.saveAndFlush(employee);
    }

    public void updateEmployee(EmployeeUpdateRequest request) {
//        kiem tra request null
        if (request == null) {
            throw new IllegalArgumentException("Request is Null");
        }
        Employee findEmployee = employeeRepository.findFirstByEmployeeIdAndStatus(request.getId(), CustomerManagement.OPEN);
//        employee null
        if (findEmployee == null) {
            throw new IllegalArgumentException("Employee not found");
        }
        BeanUtils.copyProperties(request, findEmployee);
        employeeRepository.saveAndFlush(findEmployee);
    }

    public void deleteEmployee(EmployeeDeleteRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is Null");
        }
        Employee findEmployee = employeeRepository.findFirstByEmployeeIdAndStatus(request.getId(), CustomerManagement.OPEN);
        if (findEmployee == null) {
            throw new IllegalArgumentException("Employee not found");
        }
        findEmployee.setStatus(CustomerManagement.CLOSED);
    }

    public List<GetListCustomerResponse> getCustomerByInformation(GetListCustomerRequest request) {
        return queryEmployee.getCustomerByInformation(request);
    }

    public Boolean getCustomerByDepartmentId(Long id) {
        if (id == null) {
            return false;
        }
        List<Employee> findEmployee = employeeRepository.findAllByDepartmentIdAndStatus(id, CustomerManagement.OPEN);
        if (findEmployee.size() != 0) {
            return false;
        }
        return true;
    }

    public Boolean getCustomerByJobId(Long id) {
        if (id == null) {
            return false;
        }
        List<Employee> findEmployeesByJobId = employeeRepository.findAllByJobIdAndStatus(id, CustomerManagement.OPEN);
        if (findEmployeesByJobId.size() != 0) {
            return false;
        }
        return true;
    }
}
