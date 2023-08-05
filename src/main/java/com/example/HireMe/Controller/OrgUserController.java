package com.example.HireMe.Controller;

import com.example.HireMe.Functions;
import com.example.HireMe.Model.*;
import com.example.HireMe.Repository.ApplicantJobHistoryRepository;
import com.example.HireMe.Repository.JobPostRepository;
import com.example.HireMe.Service.JobService;
import com.example.HireMe.Service.SkillsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/org")
public class OrgUserController {
    private final JobService jobService;
    private final JobPostRepository jobPostRepository;
    private final SkillsService skillsService;
    Functions functions = new Functions();
    private final ApplicantJobHistoryRepository applicantJobHistoryServiceRepository;

    public OrgUserController(JobService jobService, JobPostRepository jobPostRepository, SkillsService skillsService, ApplicantJobHistoryRepository applicantJobHistoryServiceRepository) {
        this.jobService = jobService;
        this.jobPostRepository = jobPostRepository;
        this.skillsService = skillsService;
        this.applicantJobHistoryServiceRepository = applicantJobHistoryServiceRepository;
    }


    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboardorg";
    }
    @GetMapping("/post-job")
    public String showJobForm(Model model) {
        List<Skills> skillList = skillsService.getall(); // Fetch skills from the database using your service
        model.addAttribute("skillList", skillList);
        return "jobform";
    }
    @PostMapping("/post-job")
    public String storeJob(@RequestParam("title") String title, @RequestParam("location") String location, @RequestParam("desc") String desc, @RequestParam(name = "skills", required = false) List<String> selectedSkills, Model model) {
        JobPost jobPost = new JobPost();
        jobPost.setJobTitle(title);
        jobPost.setLocation(location);
        jobPost.setDetail(desc);
        Organisation organisation = new Organisation();

        // Convert LocalDate to Date
        jobPost.setDateposted(functions.getdate());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        organisation.setId(((Organisation) authentication.getPrincipal()).getId());
        jobPost.setPosted_by(organisation);
        jobService.save(jobPost);
        List<String> selected = selectedSkills;
        skillsService.storeskills(selected, jobPost);
        return "jobform";
    }
    @GetMapping("/manage-jobs")
    public String showJobs(Model model) {
        List<JobPost> jobPosts = jobService.searchJobpost();
        model.addAttribute("jobPosts",jobPosts);
        return "managejobs";
    }
    @GetMapping("/manage-applications")
    public String showApplications(Model model) {
        Organisation org = functions.getOrganisation();
        List<JobPost> jobPosts = jobPostRepository.findJobPostByPostedby(org.getId());
        model.addAttribute("jobposts",jobPosts);


        return "manageapplicationall";
    }
    @PostMapping("/showApplicants")
    public String showApplicants(@RequestParam("jobPostId") int jobPostId, Model model) {
        List<ApplicantJobHistory> applicantJobHistory = applicantJobHistoryServiceRepository.getApplicantJobHistoryByJob_id(jobPostId);
        model.addAttribute("applicants",applicantJobHistory);

        return "manageapplicationmain";
    }

    @PostMapping("/statuschange")
    public String changestatus(@RequestParam("historyid") int historyId, @RequestParam("option") String option, Model model) {

        applicantJobHistoryServiceRepository.updateStatusById(historyId,option);
        int jobPostId = applicantJobHistoryServiceRepository.getApplicantJobHistoryById(historyId).getJob_id().getId();
        List<ApplicantJobHistory> applicantJobHistory = applicantJobHistoryServiceRepository.getApplicantJobHistoryByJob_id(jobPostId);

        model.addAttribute("applicants",applicantJobHistory);
        return "manageapplicationmain";
    }





}
