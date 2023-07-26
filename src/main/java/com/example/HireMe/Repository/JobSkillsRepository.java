package com.example.HireMe.Repository;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.JobSkills;
import com.example.HireMe.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillsRepository extends JpaRepository<JobSkills,Long> {
}
