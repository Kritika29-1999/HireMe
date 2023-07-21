package com.example.HireMe.Model;

import javax.persistence.*;

@Entity
@Table(name = "job_post")
public class JobPost {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "job_post_id")
   private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posted_by", nullable = false)
   private Organisation posted_by;

    @Column(name = "job_detail")
    private String detail;

    @Column(name = "job_location")
    private String location;
    @Column(name = "job_status")
    private String status;
    @Column(name = "total_applicant")
    private int totalapplicant;
    @Column(name = "job_title")
    private String JobTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organisation getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(Organisation posted_by) {
        this.posted_by = posted_by;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalapplicant() {
        return totalapplicant;
    }

    public void setTotalapplicant(int totalapplicant) {
        this.totalapplicant = totalapplicant;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }
}
