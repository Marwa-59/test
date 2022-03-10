package com.wingo.ReferCode.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wingo.ActivationCode.Entity.ActivationCode;
import com.wingo.Coupon.Entity.Coupon;
import com.wingo.WingoUser.Entity.WingoUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "referCode")
public class ReferCode {
    @Id
    @Column(name = "referCode_id", updatable = false)
    private String referCodeId = UUID.randomUUID().toString();

    @Column(name = "code")
    private String code;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "usedBy")
    private Integer usedBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activationCode_id", referencedColumnName = "activation_id")
    private ActivationCode activationCode;

    @OneToOne(mappedBy = "referCode")
    private WingoUser wingoUser;


    @OneToOne
    @JoinColumn(name = "refer_id")
    private ReferCode referCode;

    @OneToOne(mappedBy = "referCode")
    private ReferCode referCodeSet;

    @JsonBackReference
    public ReferCode getReferCode() {
        return referCode;
    }



}
