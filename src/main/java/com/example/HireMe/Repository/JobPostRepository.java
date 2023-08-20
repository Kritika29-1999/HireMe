package com.example.HireMe.Repository;

import com.example.HireMe.Model.ApplicantJobHistory;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE JobPost jp SET jp.status = 'Closed' WHERE jp.id = :jpid ")
    void setjobstatustoclose(int jpid);
    @Query("SELECT j FROM JobPost j WHERE (LOWER(j.JobTitle) LIKE %:keyword% OR LOWER(j.detail) LIKE %:keyword%) AND j.status!='Closed'")
    List<JobPost> findByJobTitleOrDetailContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT j FROM JobPost j WHERE j.posted_by.id = :orgId")

    List<JobPost> findJobPostByPostedby(@Param("orgId") int id);
    @Modifying
    @Query("UPDATE JobPost e SET e.totalapplicant = :totalApplicant WHERE e.id = :id")
    void updateTotalApplicant(@Param("id") int id, @Param("totalApplicant") int totalA);
    JobPost getJobPostsById(int id);
}
