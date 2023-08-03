package com.example.HireMe.Repository;

import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Long> {
    @Query("SELECT j FROM JobPost j WHERE LOWER(j.JobTitle) LIKE %:keyword% OR LOWER(j.detail) LIKE %:keyword%")
    List<JobPost> findByJobTitleOrDetailContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT j FROM JobPost j WHERE j.posted_by.id = :orgId")

    List<JobPost> findJobPostByPostedby(@Param("orgId") int id);
}
