package com.example.HireMe.Service;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Repository.ApplicantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public Applicant findByEmail(String email) {
        return applicantRepository.findByEmail(email);
    }

    public boolean authenticate(String email, String password) {
        Applicant applicant = findByEmail(email);
        if (applicant != null && applicant.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    public void signup(Applicant applicant){
        applicantRepository.save(applicant);
    }
    public Applicant save(Applicant applicant){

        applicantRepository.save(applicant);
        return applicant;
    }


    public Applicant findByID(int appid) {
        Applicant applicant = applicantRepository.findApplicantById(appid);
        return applicant;
    }
}

