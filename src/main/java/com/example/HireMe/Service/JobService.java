package com.example.HireMe.Service;

import com.example.HireMe.Repository.JobPostRepository;

public class JobService {
    private JobPostRepository jobPostRepository;
    public JobService(JobPostRepository jobPostRepository){
        this.jobPostRepository = jobPostRepository;

    }
}
