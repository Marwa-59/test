package com.wingo.CouponRestrictions.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wingo.Coupon.Entity.Coupon;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "couponRestrictions")
public class CouponRestrictions {

    @Id
    @Column(name = "coupon_restrictions_id", updatable = false)
    private String coupon_restrictions_id = UUID.randomUUID().toString();

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "coupon_id", nullable = true)
    private Coupon coupon;

    @JsonBackReference
    public Coupon getCoupon() {
        return coupon;
    }

}
