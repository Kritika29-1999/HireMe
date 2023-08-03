package com.example.HireMe.Repository;

import com.example.HireMe.Model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
    Skills findSkillsBySkillname(String name);
    @Query("SELECT j FROM Skills j WHERE j.skill_id = :orgId")

    Skills findSkillsBySkill_id(@Param("orgId") int id);


}
