package com.example.HireMe.Controller;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Model.Skills;
import com.example.HireMe.Service.JobService;
import com.example.HireMe.Service.SkillsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/org")
public class OrgUserController {
    private final JobService jobService;
    private final SkillsService skillsService;

    public OrgUserController(JobService jobService, SkillsService skillsService) {
        this.jobService = jobService;
        this.skillsService = skillsService;
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
        LocalDate localDate = LocalDate.now();
        Organisation organisation = new Organisation();

        // Convert LocalDate to Date
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        jobPost.setDateposted(date);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        organisation.setId(((Organisation) authentication.getPrincipal()).getId());
        jobPost.setPosted_by(organisation);
        jobService.save(jobPost);
        List<String> selected = selectedSkills;
        skillsService.storeskills(selected, jobPost);
        return "jobform";
    }
    @GetMapping("/manage-jobs")
    public String showJobs() {

        return "managejobs";
    }

}
