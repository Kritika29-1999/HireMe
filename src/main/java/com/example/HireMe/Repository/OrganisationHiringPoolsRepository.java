package com.example.HireMe.Repository;

import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Model.OrganisationHiringPools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationHiringPoolsRepository extends JpaRepository<OrganisationHiringPools, Long> {

     List<OrganisationHiringPools> findOrganisationHiringPoolsByOrganisationid(Organisation id);
}
