package com.example.HireMe.Repository;

import com.example.HireMe.Model.HiringPools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringPoolsRepository extends JpaRepository<HiringPools,Long> {
    @Query("SELECT j FROM HiringPools j WHERE j.pool_name = :poolname")

    HiringPools getHiringPoolsByPool_name(@Param("poolname") String name);
}
