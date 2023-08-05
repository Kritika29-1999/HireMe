package com.example.HireMe.Model;

import javax.persistence.*;

@Entity
@Table(name = "hiring_pool")
public class HiringPools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pool_id")
    private int pool_id;
    @Column(name = "pool_name")
    private String pool_name;

    public int getPool_id() {
        return pool_id;
    }

    public void setPool_id(int pool_id) {
        this.pool_id = pool_id;
    }

    public String getPool_name() {
        return pool_name;
    }

    public void setPool_name(String pool_name) {
        this.pool_name = pool_name;
    }
}
