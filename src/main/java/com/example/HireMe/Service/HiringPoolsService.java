package com.example.HireMe.Service;

import com.example.HireMe.Model.HiringPools;
import com.example.HireMe.Repository.HiringPoolsRepository;
import org.springframework.stereotype.Service;

@Service
public class HiringPoolsService {
    private HiringPoolsRepository hiringPoolsRepository;
    public HiringPoolsService (HiringPoolsRepository hiringPoolsRepository){
        this.hiringPoolsRepository = hiringPoolsRepository;
    }
    public void savehiringpool(HiringPools hiringPools){
        hiringPoolsRepository.save(hiringPools);
    }
    public HiringPools getHiringPoolDetailsByName(String name){

       return hiringPoolsRepository.getHiringPoolsByPool_name(name);
    }
    public HiringPools getHiringpoolbyid(int id){

        return hiringPoolsRepository.getHiringPoolsByPool_id(id);
    }
}
