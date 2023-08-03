package com.example.HireMe.Repository;

import com.example.HireMe.Model.ApplicantJobHistory;
import com.example.HireMe.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApplicantJobHistoryRepository extends JpaRepository<ApplicantJobHistory, Long> {
    @Query("SELECT j FROM ApplicantJobHistory j WHERE j.job_id.id = :orgId")

    List<ApplicantJobHistory> getApplicantJobHistoryByJob_id(@Param("orgId") int id);
    ApplicantJobHistory getApplicantJobHistoryById(int id);
    @Modifying
    @Transactional
    @Query("UPDATE ApplicantJobHistory ajh SET ajh.status = :newStatus WHERE ajh.id = :historyId")
    void updateStatusById(int historyId, String newStatus);
}
