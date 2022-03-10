package com.wingo.CouponStatus.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wingo.Coupon.Entity.Coupon;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "couponStatus")
public class CouponStatus {

    @Id
    @Column(name = "coupon_status_id", updatable = false)
    private String coupon_status_id = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "couponStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Coupon> coupons = new ArrayList<>();

    @JsonBackReference
    public List<Coupon> getCoupons() {
        return coupons;
    }
}
