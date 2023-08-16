package com.example.HireMe.Service;

import com.example.HireMe.Model.Applicant;
import com.example.HireMe.Model.HiringPools;
import com.example.HireMe.Model.Organisation;
import com.example.HireMe.Model.ReferredCandidateHistory;
import com.example.HireMe.Repository.OrganisationRepository;
import com.example.HireMe.Repository.ReferredCandidateHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferredCandidateHistoryService {
    private ReferredCandidateHistoryRepository referredCandidateHistoryRepository;

    public ReferredCandidateHistoryService(ReferredCandidateHistoryRepository referredCandidateHistoryRepository) {
        this.referredCandidateHistoryRepository = referredCandidateHistoryRepository;
    }
    public void insertvalue(ReferredCandidateHistory referredCandidateHistory){

        referredCandidateHistoryRepository.save(referredCandidateHistory);
    }
    public List<ReferredCandidateHistory> getlistfrompoolid(int id){
        HiringPools hp = new HiringPools();
        hp.setPool_id(id);
        return referredCandidateHistoryRepository.findReferredCandidateHistoriesByRefto(hp);
    }
    public List<Object> getlistfrompoolidwithurl(int id){

        return referredCandidateHistoryRepository.findcandidiatehistorywithurl(id);
    }
    public int gettotalreferralcount(int applicantid){
        return referredCandidateHistoryRepository.gettotalreferals(applicantid);
    }
}
