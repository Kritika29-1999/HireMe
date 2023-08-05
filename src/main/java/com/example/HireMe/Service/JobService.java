package com.example.HireMe.Service;

import com.example.HireMe.AuthenticationCredentials;
import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Repository.JobPostRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobService {
    private JobPostRepository jobPostRepository;
    private AuthenticationCredentials authenticationCredentials = new AuthenticationCredentials();
    public JobService(JobPostRepository jobPostRepository){
        this.jobPostRepository = jobPostRepository;

    }
    public List<JobPost> searchJobpost(String text){
        return jobPostRepository.findByJobTitleOrDetailContainingIgnoreCase(text);

    }
    public List<JobPost> searchJobpost(){
        Organisation organisation = new Organisation();
        Authentication authentication = authenticationCredentials.getAuth();
        organisation.setId(((Organisation) authentication.getPrincipal()).getId());

        return jobPostRepository.findJobPostByPostedby(organisation.getId());


    }
    public void save(JobPost jobPost){

        jobPostRepository.save(jobPost);

    }

    public void update(int id, int totalA){
        jobPostRepository.updateTotalApplicant( id, totalA);


    }
    public JobPost getjobPostbyId(int id){
       return  jobPostRepository.getJobPostsById(id);
    }

}

