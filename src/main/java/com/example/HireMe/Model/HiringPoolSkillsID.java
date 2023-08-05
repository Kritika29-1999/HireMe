package com.example.HireMe.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HiringPoolSkillsID implements Serializable {
    @Column(name = "hiring_pool_id")
    private int hiringpoolId;

    @Column(name = "skill_id")
    private int skillId;

    public int getHiringpoolId() {
        return hiringpoolId;
    }

    public void setHiringpoolId(int hiringpoolId) {
        this.hiringpoolId = hiringpoolId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}
