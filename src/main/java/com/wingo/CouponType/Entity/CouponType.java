package com.wingo.CouponType.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wingo.Coupon.Entity.Coupon;
import com.wingo.FavoriteBusiness.Entity.FavoriteBusiness;
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
@Table(name = "couponType")
public class CouponType {
    @Id
    @Column(name = "coupon_type_id", updatable = false)
    private String coupon_type_id = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "couponType", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Coupon> coupons = new ArrayList<>();

    @JsonBackReference
    public List<Coupon> getCoupons() {
        return coupons;
    }
}
