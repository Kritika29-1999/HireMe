package com.example.HireMe.Repository;

import com.example.HireMe.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Long> {
     Organisation save(Organisation organisation);
}
