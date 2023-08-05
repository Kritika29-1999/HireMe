package com.example.HireMe.Model;

import javax.persistence.*;

@Entity
@Table(name = "organisation_hiring_pools")
public class OrganisationHiringPools {
    @EmbeddedId
    private OrganisationHiringPoolsID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("organisationId")
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organisation organisationid;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("poolId")
    @JoinColumn(name = "pool_id", nullable = false)
    private HiringPools poolid;

    public OrganisationHiringPoolsID getId() {
        return id;
    }

    public void setId(OrganisationHiringPoolsID id) {
        this.id = id;
    }

    public Organisation getOrganisationid() {
        return organisationid;
    }

    public void setOrganisationid(Organisation organisationid) {
        this.organisationid = organisationid;
    }

    public HiringPools getPoolid() {
        return poolid;
    }

    public void setPoolid(HiringPools poolid) {
        this.poolid = poolid;
    }
}
