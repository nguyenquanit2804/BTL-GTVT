package com.gtvt.backendcustomermanagement.controller;

import com.gtvt.backendcustomermanagement.factory.ResponseFactory;
import com.gtvt.backendcustomermanagement.model.request.*;
import com.gtvt.backendcustomermanagement.model.response.GetDetailByIdCustomerResponse;
import com.gtvt.backendcustomermanagement.model.response.GetListCustomerResponse;
import com.gtvt.backendcustomermanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST, path = "/test")
    public ResponseEntity<?> test() {
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public ResponseEntity<?> addEmployee(@ModelAttribute EmployeeCreateRequest request) {
        employeeService.addEmployee(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public ResponseEntity<?> updateEmployee(@ModelAttribute EmployeeUpdateRequest request) {
        employeeService.updateEmployee(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public ResponseEntity<?> deleteEmployee(@RequestBody EmployeeDeleteRequest request) {
        employeeService.deleteEmployee(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/get-detail")
    public ResponseEntity<?> getCustomerByInformation(@RequestBody GetListCustomerRequest request) {
      List<GetListCustomerResponse> response = employeeService.getCustomerByInformation(request);
        return responseFactory.success(response);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/get-detail-by-id")
    public ResponseEntity<?> getCustomerByInformationById(@RequestBody GetDetailByIdCustomerRequest request) {
        GetDetailByIdCustomerResponse response = employeeService.getDetailByIdCustomer(request);
        return responseFactory.success(response);
    }

//    getCustomerByInformation




}

