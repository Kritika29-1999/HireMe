package com.example.HireMe.Service;

import com.example.HireMe.Model.ApplicantJobHistory;
import com.example.HireMe.Repository.ApplicantJobHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicantJobHistoryService {
    private final ApplicantJobHistoryRepository applicantJobHistoryRepository;

    public ApplicantJobHistoryService(ApplicantJobHistoryRepository applicantJobHistoryRepository) {
        this.applicantJobHistoryRepository = applicantJobHistoryRepository;
    }


    public void insert(ApplicantJobHistory applicantJobHistory){
        applicantJobHistoryRepository.save(applicantJobHistory);
    }

}
