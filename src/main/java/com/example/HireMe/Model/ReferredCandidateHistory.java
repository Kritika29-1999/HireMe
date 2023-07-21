package com.example.HireMe.Model;

import javax.persistence.*;

@Entity
@Table(name = "referred_candidate_history")
public class ReferredCandidateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referred_by", nullable = false)
    private Organisation refby;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referred_to", nullable = false)
    private Organisation refto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referred_candidate", nullable = false)
    private Applicant refcandidate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_id", nullable = false)
    private JobPost jobid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organisation getRefby() {
        return refby;
    }

    public void setRefby(Organisation refby) {
        this.refby = refby;
    }

    public Organisation getRefto() {
        return refto;
    }

    public void setRefto(Organisation refto) {
        this.refto = refto;
    }

    public Applicant getRefcandidate() {
        return refcandidate;
    }

    public void setRefcandidate(Applicant refcandidate) {
        this.refcandidate = refcandidate;
    }

    public JobPost getJobid() {
        return jobid;
    }

    public void setJobid(JobPost jobid) {
        this.jobid = jobid;
    }
}
