package com.gtvt.backendcustomermanagement.repository;

import com.gtvt.backendcustomermanagement.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    Job findFirstByJobTitleAndStatus(String jobName , Long status);

    Job  findFirstByJobIdAndStatus(Long departmentId , Long status);

}
