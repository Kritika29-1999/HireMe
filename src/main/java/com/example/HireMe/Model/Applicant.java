package com.example.HireMe.Model;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "applicant")
public class Applicant implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private int id;

    public Applicant() {

    }

    public int getId() {
        return id;
    }
    @Column(name = "firstname")

    private String firstname;
    @Column(name = "lastname")

    private String lastname;
    @Column(name = "email")

    private String email;
    @Column(name = "phone_nos")

    private String phone;
    @Column(name = "Profile_description")

    private String profiledesc;
    @Column(name = "verified")

    private boolean verified;
    @Column(name = "date_joined")

    private Date joined;
    @Column(name = "dob")


    private String dob;
    @Column(name = "gender")

    private String gender;
    @Column(name = "designation")

    private String designation;
    @Column(name = "password")

    private String password;

    public String getProfiledesc() {
        return profiledesc;
    }

    public void setProfiledesc(String profiledesc) {
        this.profiledesc = profiledesc;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_APPLICANT"));
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}
