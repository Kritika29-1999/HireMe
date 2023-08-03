package com.example.HireMe.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applicant_job_history")
public class ApplicantJobHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobPost job_id;
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    private Date date;
    @Column(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Applicant getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(Applicant applicant_id) {
        this.applicant_id = applicant_id;
    }

    public JobPost getJob_id() {
        return job_id;
    }

    public void setJob_id(JobPost job_id) {
        this.job_id = job_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
