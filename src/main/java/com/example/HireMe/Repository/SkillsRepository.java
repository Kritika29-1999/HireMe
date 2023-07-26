package com.example.HireMe.Repository;

import com.example.HireMe.Model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
    Skills findSkillsBySkillname(String name);
}
