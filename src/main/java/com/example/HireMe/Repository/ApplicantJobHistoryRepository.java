package com.example.HireMe.Repository;

import com.example.HireMe.Model.ApplicantJobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantJobHistoryRepository extends JpaRepository<ApplicantJobHistory, Long> {
}
