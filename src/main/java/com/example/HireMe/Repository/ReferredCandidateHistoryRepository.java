package com.example.HireMe.Repository;

import com.example.HireMe.Model.HiringPools;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Model.ReferredCandidateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ReferredCandidateHistoryRepository extends JpaRepository<ReferredCandidateHistory, Long> {
    List<ReferredCandidateHistory> findReferredCandidateHistoriesByRefto(HiringPools hiringPools);
    @Query("SELECT DISTINCT j FROM ReferredCandidateHistory j left join ApplicantJobHistory a on j.jobid=a.job_id where j.refto.pool_id=:orgId")
    List<Object> findcandidiatehistorywithurl(@Param("orgId") int id);
    @Query("SELECT COUNT(j) FROM ReferredCandidateHistory j WHERE j.refcandidate.id = :appid")
    int gettotalreferals(@Param("appid") int appid);

}
