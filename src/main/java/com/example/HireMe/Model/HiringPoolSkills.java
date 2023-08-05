package com.example.HireMe.Model;

import javax.persistence.*;

@Entity
@Table(name = "hiring_pool_skills")
public class HiringPoolSkills {
    @EmbeddedId
    private HiringPoolSkillsID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("hiringpoolId")
    @JoinColumn(name = "hiring_pool_id", nullable = false)
    private HiringPools hiringpoolid;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    @JoinColumn(name = "skill_id", nullable = false)
    private Skills skillid;

    public HiringPoolSkillsID getId() {
        return id;
    }

    public void setId(HiringPoolSkillsID id) {
        this.id = id;
    }

    public HiringPools getHiringpoolid() {
        return hiringpoolid;
    }

    public void setHiringpoolid(HiringPools hiringpoolid) {
        this.hiringpoolid = hiringpoolid;
    }

    public Skills getSkillid() {
        return skillid;
    }

    public void setSkillid(Skills skillid) {
        this.skillid = skillid;
    }
}
