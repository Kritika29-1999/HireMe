package com.example.HireMe.Repository;

import com.example.HireMe.Model.ReferredCandidateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferredCandidateHistoryRepository extends JpaRepository<ReferredCandidateHistory, Long> {
}
