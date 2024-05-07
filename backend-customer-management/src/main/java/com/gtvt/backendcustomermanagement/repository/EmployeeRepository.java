package com.gtvt.backendcustomermanagement.repository;

import com.gtvt.backendcustomermanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findFirstByEmployeeIdAndStatus(Long id , Long status);

    List<Employee> findAllByDepartmentIdAndStatus(Long id, Long status);

    List<Employee> findAllByJobIdAndStatus(Long id, Long status);

}
