package com.example.HireMe.Service;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Repository.ApplicantRepository;
import com.example.HireMe.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LocalOrgDetailsService implements UserDetailsService {
    @Autowired
    private final OrganisationRepository organisationRepository;


    public LocalOrgDetailsService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Organisation user = organisationRepository.findByOrgemail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return organisationRepository.findByOrgemail(email);
    }
    public boolean authenticate(String email, String password) {
        // Find the applicant by email
        Organisation organisation = organisationRepository.findByOrgemail(email);
        if (organisation == null) {
            return false; // Applicant not found
        }
        if (organisation.getPassword().equals(password)) {

            return true; // Authentication successful
        } else {
            return false; // Incorrect password
        }
    }
}
