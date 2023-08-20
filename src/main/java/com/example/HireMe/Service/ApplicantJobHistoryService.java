package com.example.HireMe.Service;

import com.example.HireMe.Functions;
import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.ApplicantJobHistory;
import com.example.HireMe.Model.JobPost;
import com.example.HireMe.Repository.ApplicantJobHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantJobHistoryService {
    private final ApplicantJobHistoryRepository applicantJobHistoryRepository;
    private Functions functions = new Functions();

    public ApplicantJobHistoryService(ApplicantJobHistoryRepository applicantJobHistoryRepository) {
        this.applicantJobHistoryRepository = applicantJobHistoryRepository;
    }


    public void insert(ApplicantJobHistory applicantJobHistory){
        applicantJobHistoryRepository.save(applicantJobHistory);
    }
    public List<ApplicantJobHistory> getalljobs(){
       return applicantJobHistoryRepository.getApplicantJobHistoryByApplicant_id(functions.getApplicant().getId());
    }

    public void setstatus(int jobPostId, int applicantid) {
        applicantJobHistoryRepository.setstatustoreferred(applicantid,jobPostId);
    }

    public boolean findbyappidandjobid(int jobPost, int id) {

        return applicantJobHistoryRepository.existsApplicantJobHistoryByApplicant_idIsAndJob_idIs(id,jobPost);
    }
    public boolean finddatabyjobidappid(int jobPost, int id) {

        return applicantJobHistoryRepository.existsApplicantJobHistoryByApplicant_idIsAndJob_idIs(id,jobPost);
    }
}
