package com.example.HireMe.Controller;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.JobSkills;
import com.example.HireMe.Model.Skills;
import com.example.HireMe.FileConfig.FileService;
import com.example.HireMe.Repository.JobPostRepository;
import com.example.HireMe.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")

public class ApplicationContoller {
    private final FileService fileService;
    private final JobService jobService;
    private final JobSkillsService jobSkillsService;
    private final ApplicantService applicantService;
    private final SkillsService skillsService;

    private final JobPostRepository jobPostRepository;

    public ApplicationContoller(FileService fileService, JobService jobService, JobSkillsService jobSkillsService, ApplicantService applicantService, SkillsService skillsService, JobPostRepository jobPostRepository) {
        this.fileService = fileService;
        this.jobService = jobService;
        this.jobSkillsService = jobSkillsService;
        this.applicantService = applicantService;
        this.skillsService = skillsService;
        this.jobPostRepository = jobPostRepository;
    }

    @GetMapping("/profile")
    public String showApplicantDashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String firstName = ((Applicant) authentication.getPrincipal()).getFirstname();
        String lastname = ((Applicant) authentication.getPrincipal()).getLastname();


        model.addAttribute("firstname", firstName);
        model.addAttribute("lastname", lastname);

        return "profilepage";
    }
    @PostMapping("/search")
    public String submitForm(@RequestParam("searchtext") String searchtext, Model model) {
       List<JobPost> jp = jobService.searchJobpost(searchtext);
        for (JobPost item : jp) {
            System.out.println(item.getJobTitle());
        }

        model.addAttribute("jp",jp);
        return "jobresults";
    }
    @GetMapping("/apply/{id}")
        public String submitApplication(@PathVariable("id") int jobId, Model model) {
           JobPost jobPost = new JobPost();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int Appid = ((Applicant) authentication.getPrincipal()).getId();
           jobPost.setId(jobId);
           List<JobSkills> jobSkills = jobSkillsService.findskillsbyjob(jobId);
           List<String> skills = new ArrayList<>();

           for(JobSkills js:jobSkills){
               Skills s = skillsService.findbyid(js.getSkills().getSkill_id());
               skills.add(s.getSkillname());
           }

           Applicant applicant = applicantService.findByID(Appid);
           model.addAttribute("jobpost",jobPost);
        model.addAttribute("applicant",applicant);
        model.addAttribute("skills",skills);


        return "jobapplicationform";
        }






}
