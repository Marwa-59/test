package com.wingo.Coupon.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wingo.ActivationCode.Entity.ActivationCode;
import com.wingo.ActivationCoupon.Entity.ActivationCoupon;
import com.wingo.Branches.Entity.Branches;
import com.wingo.CouponRestrictions.Entity.CouponRestrictions;
import com.wingo.CouponStatus.Entity.CouponStatus;
import com.wingo.CouponType.Entity.CouponType;
import com.wingo.Role.Entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "coupon")
public class Coupon {

    @Id
    @Column(name = "coupon_id", updatable = false)
    private String coupon_id = UUID.randomUUID().toString();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "fromDate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fromDate;

    @Column(name = "toDate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date toDate;

    @Column(name = "oneUse")
    private Boolean oneUse;

    @Column(name = "delivery")
    private Boolean delivery;

    @Column(name = "onPlace")
    private Boolean onPlace;

    @Column(name = "allBranches")
    private Boolean allBranches;

    @Column(name = "accepted")
    private Boolean accepted;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "serialNumber")
    private Integer serialNumber;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "coupon_type_id", nullable = true)
    private CouponType couponType;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "coupon_status_id", nullable = true)
    private CouponStatus couponStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<CouponRestrictions> couponRestrictions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<ActivationCoupon> activationCoupons = new ArrayList<>();


    @ManyToMany( fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Branches> branches = new HashSet<>();

    @JsonBackReference(value = "activation")
    public List<ActivationCoupon> getActivationCoupons() {
        return activationCoupons;
    }


}
