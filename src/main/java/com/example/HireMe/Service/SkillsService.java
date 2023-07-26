package com.example.HireMe.Service;

import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.JobSkills;
import com.example.HireMe.Model.Skills;
import com.example.HireMe.Repository.JobSkillsRepository;
import com.example.HireMe.Repository.SkillsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {
    private SkillsRepository skillsRepository;
    private JobSkillsRepository jobSkillsRepository;

    public SkillsService(SkillsRepository skillsRepository,JobSkillsRepository jobSkillsRepository) {
        this.skillsRepository = skillsRepository;
        this.jobSkillsRepository = jobSkillsRepository;
    }

    public List<Skills> getall(){
        List<Skills> skillsList = skillsRepository.findAll();
        return skillsList;
    }

    public void storeskills(List<String> selected, JobPost id) {
        for(String skillname:selected){


            JobSkills jobSkills = new JobSkills();
            Skills skills = new Skills();
            skills  = findbyname(skillname);
            jobSkills.setSkills(skills);
            jobSkills.setJobPost(id);
            jobSkillsRepository.save(jobSkills);



        }
    }
    public Skills findbyname(String name){
        return skillsRepository.findSkillsBySkillname(name);
    }

}
