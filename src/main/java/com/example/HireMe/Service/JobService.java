package com.example.HireMe.Service;

import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private JobPostRepository jobPostRepository;
    public JobService(JobPostRepository jobPostRepository){
        this.jobPostRepository = jobPostRepository;

    }
    public List<JobPost> searchJobpost(String text){
        return jobPostRepository.findByJobTitleOrDetailContainingIgnoreCase(text);

    }
}

