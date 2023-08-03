package com.example.HireMe.Repository;

import com.example.HireMe.Model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Applicant findByEmail(String email);
    Applicant findApplicantById(int id);


}
