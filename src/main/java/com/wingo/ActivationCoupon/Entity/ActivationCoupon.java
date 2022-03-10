package com.wingo.ActivationCoupon.Entity;


import com.wingo.Coupon.Entity.Coupon;
import com.wingo.ActivationCode.Entity.ActivationCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "activationCoupon")
public class ActivationCoupon {

    @Id
    @Column(name = "activation_coupon_id", updatable = false)
    private String activation_coupon_id = UUID.randomUUID().toString();

    @Column(name = "is_use")
    private Boolean isUse;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "activation_id", nullable = true)
    private ActivationCode activationCode;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "coupon_id", nullable = true)
    private Coupon coupon;
}
