package com.example.HireMe.Service;

import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Repository.OrganisationRepository;
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
    public void signup(Organisation organisation){
        save(organisation);
    }

}
