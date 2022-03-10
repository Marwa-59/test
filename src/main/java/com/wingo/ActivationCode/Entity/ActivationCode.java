package com.wingo.ActivationCode.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wingo.ActivationCoupon.Entity.ActivationCoupon;
import com.wingo.FavoriteBusiness.Entity.FavoriteBusiness;
import com.wingo.ReferCode.Entity.ReferCode;
import com.wingo.WingoUser.Entity.WingoUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "activationCode")
public class ActivationCode {

    @Id
    @Column(name = "activation_id", updatable = false)
    private String activationId = UUID.randomUUID().toString();

    @Column(name = "omt_code")
    private String omtCode;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Column(name = "active")
    private Boolean active;

    @OneToOne(mappedBy = "activationCode",cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WingoUser wingoUser;

    @OneToOne(mappedBy = "activationCode")
    private ReferCode referCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "activationCode", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<ActivationCoupon> activationCoupons = new ArrayList<>();

    @JsonBackReference(value ="active")
    public List<ActivationCoupon>  getActivationCoupons() {
        return activationCoupons;
    }
}
