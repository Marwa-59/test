package com.wingo.Branches.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wingo.Business.Entity.Business;
import com.wingo.Coupon.Entity.Coupon;
import com.wingo.FavoriteBusiness.Entity.FavoriteBusiness;
import com.wingo.Regions.Entity.Region;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Branches")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Branches {
    @Id
    @Column(name = "branch_id", updatable = false)
    private String branchId = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json",name="google_maps_coordinates")
    private Map<String, String> googleMapsCoordinates;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "device_token")
    private String deviceToken;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "business_id", nullable = true)
    private Business business;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "region_id", nullable = true)
    private Region region;

    @ManyToMany( fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Coupon> coupons = new HashSet<>();

    @JsonBackReference
    public Business getBusiness() {
        return business;
    }


}
