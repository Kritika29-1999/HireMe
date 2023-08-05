package com.example.HireMe.Service;

import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Model.OrganisationHiringPools;
import com.example.HireMe.Repository.OrganisationHiringPoolsRepository;
import com.example.HireMe.Repository.OrganisationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationHiringPoolsService {
    private OrganisationHiringPoolsRepository organisationHiringPoolsRepository;
    public OrganisationHiringPoolsService (OrganisationHiringPoolsRepository organisationHiringPoolsRepository){
        this.organisationHiringPoolsRepository = organisationHiringPoolsRepository;
    }

    public List<OrganisationHiringPools> getOrgList(int organisation_id) {
        Organisation org = new Organisation();
        org.setId(organisation_id);
        return organisationHiringPoolsRepository.findOrganisationHiringPoolsByOrganisationid(org);
    }
    public void saveorganisationpool(OrganisationHiringPools organisationHiringPools){
        organisationHiringPoolsRepository.save(organisationHiringPools);
    }
}
