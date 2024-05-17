package com.gtvt.backendcustomermanagement.controller;

import com.gtvt.backendcustomermanagement.factory.ResponseFactory;
import com.gtvt.backendcustomermanagement.model.request.*;
import com.gtvt.backendcustomermanagement.model.response.GetListDepartmentResponse;
import com.gtvt.backendcustomermanagement.model.response.GetListJobResponse;
import com.gtvt.backendcustomermanagement.services.DepartmentService;
import com.gtvt.backendcustomermanagement.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public ResponseEntity<?> addJob(@RequestBody JobCreateRequest request) {
        jobService.addJob(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public ResponseEntity<?> updateJob(@RequestBody JobUpdateRequest request) {
        jobService.updateJob(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public ResponseEntity<?> deleteJob(@RequestBody JobDeleteRequest request) {
        jobService.deleteJob(request);
        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/get-detail")
    public ResponseEntity<?> getListJobDetail(@RequestBody GetListJobRequest request) {
      List<GetListJobResponse> response = jobService.getListJobDetail(request);
        return responseFactory.success(response);
    }




}

