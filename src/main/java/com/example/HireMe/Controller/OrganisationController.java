package com.example.HireMe.Controller;

import com.example.HireMe.FormUtils;
import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Repository.OrganisationRepository;
import com.example.HireMe.Service.OrganisationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/organisation")
public class OrganisationController {
    private final OrganisationRepository organisationRepository;
    private final OrganisationService organisationService;

    public OrganisationController(OrganisationRepository organisationRepository, OrganisationService organisationService) {
        this.organisationRepository = organisationRepository;
        this.organisationService = organisationService;
    }


    @GetMapping("/signup")
    public String showOrgSignup() {
        return "organisationsignup";
    }
    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("organisation") Organisation organisation) {
        organisationService.save(organisation);
        return "mainForm"; // Return the name of the success view
    }
    @GetMapping("/login")
    public String showApplicantLogin() {
        return "organisationlogin";
    }





    }

//    @GetMapping("/all")
//    public List<Applicant> getAllApplicants() {
//        return applicantRepository.findAll();
//    }

