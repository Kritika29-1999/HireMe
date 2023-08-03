package com.example.HireMe.Repository;

import com.example.HireMe.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillsRepository extends JpaRepository<JobSkills,Long> {
     List<JobSkills> findJobSkillsByJobPost(JobPost jobPost);
}
