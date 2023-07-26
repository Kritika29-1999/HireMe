package com.example.HireMe.Service;

import com.example.HireMe.Repository.JobPostRepository;
import com.example.HireMe.Repository.JobSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSkillsService {
    private JobSkillsRepository jobSkillsRepository;

    public JobSkillsService(JobSkillsRepository jobSkillsRepository) {
        this.jobSkillsRepository = jobSkillsRepository;
    }
}
