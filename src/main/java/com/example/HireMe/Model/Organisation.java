package com.example.HireMe.Model;

import javax.persistence.*;

@Entity
@Table(name = "organisation")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_reg_no")
    private int id;
    @Column(name = "org_name")
    private String orgname;
    @Column(name = "email")
    private String orgemail;
    @Column(name = "password")
    private String orgpassword;

    @Column(name = "contant_no")
    private String orgcontact;
    @Column(name = "Street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "postcode")
    private String postcode;
    @Column(name = "country")
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgemail() {
        return orgemail;
    }

    public void setOrgemail(String orgemail) {
        this.orgemail = orgemail;
    }

    public String getOrgpassword() {
        return orgpassword;
    }

    public void setOrgpassword(String orgpassword) {
        this.orgpassword = orgpassword;
    }



    public String getOrgcontact() {
        return orgcontact;
    }

    public void setOrgcontact(String orgcontact) {
        this.orgcontact = orgcontact;
    }
}
