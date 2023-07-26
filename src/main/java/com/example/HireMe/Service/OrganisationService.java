package com.example.HireMe.Service;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Repository.OrganisationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Service
public class OrganisationService {

    private OrganisationRepository organisationRepository;
    public OrganisationService (OrganisationRepository organisationRepository){
        this.organisationRepository = organisationRepository;
    }
    public Organisation save(Organisation organisation){

        organisationRepository.save(organisation);
        return organisation;
    }
    public Organisation findByEmail(String email)
    {

        return organisationRepository.findByOrgemail(email);
    }
    public Organisation findByID(int id)
    {
        return organisationRepository.findById(id);
    }

}
