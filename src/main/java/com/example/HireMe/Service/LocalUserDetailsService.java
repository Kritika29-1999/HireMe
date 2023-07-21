package com.example.HireMe.Service;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LocalUserDetailsService implements UserDetailsService {
    @Autowired
    private final ApplicantRepository applicantRepository;


    public LocalUserDetailsService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Applicant user = applicantRepository.findByEmail(email);
         if (user == null) {
             throw new UsernameNotFoundException("User not found");
         }
         return applicantRepository.findByEmail(email);
    }
    public boolean authenticate(String email, String password) {
        // Find the applicant by email
        Applicant applicant = applicantRepository.findByEmail(email);
        if (applicant == null) {
            return false; // Applicant not found
        }
        if (applicant.getPassword().equals(password)) {

            return true; // Authentication successful
        } else {
            return false; // Incorrect password
        }
    }
}
