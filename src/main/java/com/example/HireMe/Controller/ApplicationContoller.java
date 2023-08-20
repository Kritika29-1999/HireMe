package com.example.HireMe.Controller;

import com.example.HireMe.Functions;
import com.example.HireMe.Model.*;
import com.example.HireMe.FileConfig.FileService;
import com.example.HireMe.Repository.ApplicantJobHistoryRepository;
import com.example.HireMe.Repository.JobPostRepository;
import com.example.HireMe.Service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")

public class ApplicationContoller {
    private Functions functions = new Functions();
    private final FileService fileService;
    private final JobService jobService;
    private final ApplicantJobHistoryRepository applicantJobHistoryRepository;
    private final JobSkillsService jobSkillsService;
    private final ApplicantService applicantService;
    private final SkillsService skillsService;
    private final ReferredCandidateHistoryService referredCandidateHistoryService;

    private final JobPostRepository jobPostRepository;
    private final ApplicantJobHistoryService applicantJobHistoryService;

    public ApplicationContoller(FileService fileService, JobService jobService, ApplicantJobHistoryRepository applicantJobHistoryRepository, JobSkillsService jobSkillsService, ApplicantService applicantService, SkillsService skillsService, ReferredCandidateHistoryService referredCandidateHistoryService, JobPostRepository jobPostRepository, ApplicantJobHistoryService applicantJobHistoryService) {
        this.fileService = fileService;
        this.jobService = jobService;
        this.applicantJobHistoryRepository = applicantJobHistoryRepository;
        this.jobSkillsService = jobSkillsService;
        this.applicantService = applicantService;
        this.skillsService = skillsService;
        this.referredCandidateHistoryService = referredCandidateHistoryService;
        this.jobPostRepository = jobPostRepository;
        this.applicantJobHistoryService = applicantJobHistoryService;
    }

    @GetMapping("/profile")
    public String showApplicantDashboard(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String firstName = ((Applicant) authentication.getPrincipal()).getFirstname();
        String lastname = ((Applicant) authentication.getPrincipal()).getLastname();
        int totalreferrals = referredCandidateHistoryService.gettotalreferralcount(((Applicant) authentication.getPrincipal()).getId());
        Applicant app = applicantService.findByID(((Applicant) authentication.getPrincipal()).getId());
        model.addAttribute("totalreferrals", totalreferrals);
        model.addAttribute("applicant",app);
        model.addAttribute("firstname", firstName);
        model.addAttribute("lastname", lastname);

        return "profilepage";
    }
    @GetMapping("/dashboard")
    public String showmainhome(Model model) {

        String firstName = functions.getApplicant().getFirstname();
        String lastname = functions.getApplicant().getLastname();
        model.addAttribute("firstname", firstName);
        model.addAttribute("lastname", lastname);

        return "dashboard";
    }
    @GetMapping("/updateapplication/{jobid}")
        public String changeapplication(@PathVariable String jobid){
        System.out.println(jobid);

        applicantJobHistoryRepository.setstatustowithdrawn(functions.getApplicant().getId(), Integer.parseInt(jobid));
        return "redirect:/user/jobs";
        }

    @GetMapping("/jobs")
    public String showjobdashboard(Model model) {

      List<ApplicantJobHistory> allapplicantjobs = applicantJobHistoryService.getalljobs();
      Applicant applicant = applicantService.findByID(functions.getApplicant().getId());
        model.addAttribute("allapplicantjobs",allapplicantjobs);
        model.addAttribute("applicant",applicant);


        return "alljobdahsboard";
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
    @PostMapping("/editjob")
    public String editjobstatus(@RequestParam("searchtext") String searchtext, Model model) {
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
