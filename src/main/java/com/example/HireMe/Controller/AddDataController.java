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
@RequestMapping("/add")
public class AddDataController {


    @GetMapping("/hiring-pool")
    public void addhiring(){


    }



}
