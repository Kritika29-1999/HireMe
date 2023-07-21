package com.example.HireMe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")

public class ApplicationContoller {
    @GetMapping("/profile")
    public String showApplicantDashboard() {
        return "profilepage";
    }





}
