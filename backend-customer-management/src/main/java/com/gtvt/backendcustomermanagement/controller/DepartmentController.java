package com.gtvt.backendcustomermanagement.controller;

import com.gtvt.backendcustomermanagement.factory.ResponseFactory;
import com.gtvt.backendcustomermanagement.model.request.DepartmentCreateRequest;
import com.gtvt.backendcustomermanagement.model.request.DepartmentDeleteRequest;
import com.gtvt.backendcustomermanagement.model.request.DepartmentUpdateRequest;
import com.gtvt.backendcustomermanagement.model.request.GetListDepartmentRequest;
import com.gtvt.backendcustomermanagement.model.response.GetListDepartmentResponse;
import com.gtvt.backendcustomermanagement.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentCreateRequest request) {
        departmentService.addDepartment(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentUpdateRequest request) {
        departmentService.updateDepartment(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public ResponseEntity<?> deleteDepartment(@RequestBody DepartmentDeleteRequest request) {
        departmentService.deleteDepartment(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/get-detail")
    public ResponseEntity<?> getListDepartment(@RequestBody GetListDepartmentRequest request) {
      List<GetListDepartmentResponse> response = departmentService.getListDepartment(request);
        return responseFactory.success(response);
    }




}

