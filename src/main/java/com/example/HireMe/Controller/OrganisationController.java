package com.example.HireMe.Controller;

import com.example.HireMe.Model.*;
import com.example.HireMe.Repository.ApplicantJobHistoryRepository;
import com.example.HireMe.Service.LocalOrgDetailsService;
import com.example.HireMe.Service.OrganisationService;
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
import java.util.List;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/organisation")
public class OrganisationController {
    private final LocalOrgDetailsService localOrgDetailsService;
    private final OrganisationService organisationService;


    public OrganisationController(LocalOrgDetailsService localOrgDetailsService, OrganisationService organisationService) {
        this.localOrgDetailsService = localOrgDetailsService;
        this.organisationService = organisationService;
    }


    @GetMapping("/signup")
    public String showOrgSignup() {
        return "organisationsignup";
    }
    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("organisation") Organisation organisation) {
        LocalDate localDate = LocalDate.now();

        // Convert LocalDate to Date
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        organisation.setDatejoined(date);
        organisationService.save(organisation);
        return "organisationlogin"; // Return the name of the success view
    }
    @PostMapping("/logincheck")
    public String processLoginForm(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   Model model) {
        // Use the ApplicantService to authenticate the applicant
        boolean authenticated = localOrgDetailsService.authenticate(email, password);

        if (authenticated) {
            UserDetails userDetails = localOrgDetailsService.loadUserByUsername(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);


            return "redirect:/org/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "redirect:/signup";
        }
    }
    @GetMapping("/login")
    public String showApplicantLogin() {
        return "organisationlogin";
    }





    }


