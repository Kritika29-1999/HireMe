package com.example.HireMe.Controller;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Repository.JobPostRepository;
import com.example.HireMe.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")

public class ApplicationContoller {
    private final JobService jobService;
    private final JobPostRepository jobPostRepository;

    public ApplicationContoller(JobService jobService, JobPostRepository jobPostRepository) {
        this.jobService = jobService;
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





}
