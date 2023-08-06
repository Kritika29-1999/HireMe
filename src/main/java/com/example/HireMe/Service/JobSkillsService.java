package com.example.HireMe.Service;

import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.JobSkills;
import com.example.HireMe.Model.Skills;
import com.example.HireMe.Repository.JobPostRepository;
import com.example.HireMe.Repository.JobSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobSkillsService {
    private JobSkillsRepository jobSkillsRepository;

    public JobSkillsService(JobSkillsRepository jobSkillsRepository) {
        this.jobSkillsRepository = jobSkillsRepository;
    }

    public List< JobSkills> findskillsbyjob(int jobId) {
        JobPost jobPost = new JobPost();
        jobPost.setId(jobId);
        return jobSkillsRepository.findJobSkillsByJobPost(jobPost);
    }
    public List< String> findskilllistfromjob(int jobId) {
        JobPost jobPost = new JobPost();
        jobPost.setId(jobId);
        List<JobSkills> jobSkillsList = jobSkillsRepository.findJobSkillsByJobPost(jobPost);
        List<String> skillNames = jobSkillsList.stream()
                .map(jobskill -> jobskill.getSkills().getSkillname())
                .collect(Collectors.toList());


        return skillNames;
    }
}
