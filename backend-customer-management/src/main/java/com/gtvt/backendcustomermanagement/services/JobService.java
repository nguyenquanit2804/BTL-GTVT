package com.gtvt.backendcustomermanagement.services;

import com.gtvt.backendcustomermanagement.common.CustomerManagement;
import com.gtvt.backendcustomermanagement.entity.Employee;
import com.gtvt.backendcustomermanagement.entity.Job;
import com.gtvt.backendcustomermanagement.model.request.GetListJobRequest;
import com.gtvt.backendcustomermanagement.model.request.JobCreateRequest;
import com.gtvt.backendcustomermanagement.model.request.JobDeleteRequest;
import com.gtvt.backendcustomermanagement.model.request.JobUpdateRequest;
import com.gtvt.backendcustomermanagement.model.response.GetListJobResponse;
import com.gtvt.backendcustomermanagement.repository.JobRepository;
import com.gtvt.backendcustomermanagement.repository.querycustom.QueryJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    QueryJob queryJob;


    public void addJob(JobCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is null");
        }
        Job findJob = jobRepository.findFirstByJobTitleAndStatus(request.getJobName(), CustomerManagement.OPEN);
        if (findJob != null) {
            throw new IllegalArgumentException("Job exist");
        }
        Job newJob = new Job();
        newJob.setJobTitle(request.getJobName());
        if (request.getMinSalary() > request.getMaxSalary()) {
            throw new IllegalArgumentException("Min Salary cannot be larger Max Salary");
        }
        newJob.setMinSalary(request.getMinSalary());
        newJob.setMaxSalary(request.getMaxSalary());
        newJob.setStatus(CustomerManagement.OPEN);
        jobRepository.save(newJob);
    }

    public void updateJob(JobUpdateRequest request) {
        if (request == null) {
            throw new IllegalStateException("Request is null");
        }
        Job findJob = jobRepository.findFirstByJobIdAndStatus(request.getIdJob(), CustomerManagement.OPEN);
        if (findJob == null) {
            throw new IllegalStateException("Job is null");
        }
        Boolean findEmployees = employeeService.getCustomerByJobId(findJob.getJobId());
        if (findEmployees) {
            throw new IllegalArgumentException("The job is currently employees");
        }
        findJob.setJobTitle(request.getJobName());
        if (request.getMinSalary() > request.getMaxSalary()) {
            throw new IllegalArgumentException("Min Salary cannot be larger Max Salary");
        }
        findJob.setMinSalary(request.getMinSalary());
        findJob.setMaxSalary(request.getMaxSalary());
        jobRepository.save(findJob);
    }

    public void deleteJob(JobDeleteRequest request) {
        if (request == null) {
            throw new IllegalStateException("Request is null");
        }
        Job findJob = jobRepository.findFirstByJobIdAndStatus(request.getId(), CustomerManagement.OPEN);
        if (findJob == null) {
            throw new IllegalStateException("Job is null");
        }
        Boolean findEmployees = employeeService.getCustomerByJobId(findJob.getJobId());
        if (findEmployees) {
            throw new IllegalArgumentException("The job is currently employees");
        }
        findJob.setStatus(CustomerManagement.CLOSED);

        jobRepository.save(findJob);

    }

    public List<GetListJobResponse> getListJobDetail(GetListJobRequest request) {
        return queryJob.getInfoJob(request);
    }
}
