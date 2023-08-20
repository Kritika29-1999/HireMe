package com.example.HireMe.Repository;

import com.example.HireMe.Model.Applicant;
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
    @Query("SELECT j FROM ApplicantJobHistory j WHERE j.job_id.id = :orgId and j.status != 'Withdrawn'")

    List<ApplicantJobHistory> getApplicantJobHistoryByJob_id(@Param("orgId") int id);
    @Query("SELECT j FROM ApplicantJobHistory j WHERE j.applicant_id.id = :appid")

    List<ApplicantJobHistory> getApplicantJobHistoryByApplicant_id(@Param("appid") int id);
    ApplicantJobHistory getApplicantJobHistoryById(int id);
    @Modifying
    @Transactional
    @Query("UPDATE ApplicantJobHistory ajh SET ajh.status = :newStatus WHERE ajh.id = :historyId")
    void updateStatusById(int historyId, String newStatus);
    @Modifying
    @Transactional
    @Query("UPDATE ApplicantJobHistory ajh SET ajh.status = 'Referred' WHERE ajh.applicant_id.id = :applicantid and ajh.job_id.id=:jobid")
    void setstatustoreferred(int applicantid, int jobid);
    @Query("SELECT CASE WHEN COUNT(ajh) > 0 THEN true ELSE false END " +
            "FROM ApplicantJobHistory ajh " +
            "WHERE ajh.applicant_id.id = :applicantId AND ajh.job_id.id = :jobPostId")
    boolean existsApplicantJobHistoryByApplicant_idIsAndJob_idIs(int applicantId, int jobPostId);
    @Modifying
    @Transactional
    @Query("UPDATE ApplicantJobHistory ajh SET ajh.status = 'Withdrawn' WHERE ajh.applicant_id.id = :applicantid and ajh.job_id.id=:jobid")
    void setstatustowithdrawn(int applicantid, int jobid);
}

