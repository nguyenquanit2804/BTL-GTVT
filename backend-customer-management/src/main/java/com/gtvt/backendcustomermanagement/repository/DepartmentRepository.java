package com.gtvt.backendcustomermanagement.repository;

import com.gtvt.backendcustomermanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface DepartmentRepository extends JpaRepository<Department,Long> {


    Department findFirstByDepartmentName(String departmentName);

    Department findFirstByDepartmentId(Long departmentId);

    List<Department> findAllByDepartmentName(String departmentName);

}
