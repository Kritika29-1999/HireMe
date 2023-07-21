package com.example.HireMe.Controller;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Repository.ApplicantRepository;
import com.example.HireMe.Service.ApplicantService;
import com.example.HireMe.Service.LocalUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/applicants")
public class ApplicantController {
    private final ApplicantRepository applicantRepository;
    private final ApplicantService applicantService;
    private final LocalUserDetailsService localUserDetailsService;

    public ApplicantController(ApplicantRepository applicantRepository, ApplicantService applicantService, LocalUserDetailsService localUserDetailsService) {
        this.applicantRepository = applicantRepository;
        this.applicantService = applicantService;
        this.localUserDetailsService = localUserDetailsService;
    }
    @GetMapping("/signup")
    public String showApplicantSignup() {
        return "applicantsignup";
    }
    @GetMapping("/login")
    public String showApplicantLogin() {
        return "applicantslogin";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("applicant") Applicant applicant) {
        LocalDate localDate = LocalDate.now();

        // Convert LocalDate to Date
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        applicant.setJoined(date);
        applicantService.save(applicant);
        return "mainForm"; // Return the name of the success view
    }
    @GetMapping("/dashboard")
    public String showApplicantDashboard(Model model) {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() !=null) {
            boolean isapplicant = authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_APPLICANT"));
            if(isapplicant) {
                String firstName = ((Applicant) authentication.getPrincipal()).getFirstname();
                String lastname = ((Applicant) authentication.getPrincipal()).getLastname();


                model.addAttribute("firstname", firstName);
                model.addAttribute("lastname", lastname);

            }
        }
        return "dashboard";
    }


    @PostMapping("/logincheck")
    public String processLoginForm(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   Model model) {
        // Use the ApplicantService to authenticate the applicant
        boolean authenticated = localUserDetailsService.authenticate(email, password);

        if (authenticated) {
            UserDetails userDetails = localUserDetailsService.loadUserByUsername(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);


//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication != null) {
//                Object principal = authentication.getPrincipal();
//                // Check if the principal is an instance of UserDetails and then access user details
//                if (principal instanceof Applicant) {
//                    Applicant userDetails = (Applicant) principal;
//                    String username = userDetails.getEmail();
//                    // ...
//                }
//            }
//            if (authentication != null && authentication.getPrincipal() !=null) {
//                Applicant applicant = (Applicant) authentication.getPrincipal();
                // Access and use the user details as needed
//            }
            return "redirect:dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "redirect:/signup";
        }
    }

    @GetMapping("/appform")
    public String showApplicantForm(Model model) {
        model.addAttribute("applicant", new Applicant());
        return "mainForm";
    }




}

//    @GetMapping("/all")
//    public List<Applicant> getAllApplicants() {
//        return applicantRepository.findAll();
//    }

