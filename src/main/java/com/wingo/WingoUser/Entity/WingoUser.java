package com.wingo.WingoUser.Entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.wingo.ActivationCode.Entity.ActivationCode;
import com.wingo.Business.Entity.Business;
import com.wingo.Category.Entity.Category;
import com.wingo.FavoriteBusiness.Entity.FavoriteBusiness;
import com.wingo.Rate.Entity.Rate;
import com.wingo.ReferCode.Entity.ReferCode;
import com.wingo.Role.Entity.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class WingoUser {

    @Id
    @Column(name = "user_id", updatable = false)
    private String user_id = UUID.randomUUID().toString();

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @NotNull @Column(unique=true,name="phone_Number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @Column(name = "deviceToken")
    private String deviceToken;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "passwordVerified")
    private Boolean passwordVerified;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json",name="google_maps_coordinates")
    private Map<String, String> googleMapsCoordinates;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rate_id", nullable = true)
    private Rate rate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<FavoriteBusiness> favoriteBusinesses = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ActivationCode activationCode;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "referCode_id")
    private ReferCode referCode;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "business_id", nullable = true)
    private Business business;

    public WingoUser(String user_id, String email) {
        this.user_id = user_id;
        this.email = email;
    }

    @JsonBackReference
    public Rate getRate() {
        return rate;
    }

    @JsonBackReference(value = "activation")
    public ActivationCode getActivationCode() {
        return activationCode;
    }

    @JsonBackReference(value ="refer")
    public ReferCode getReferCode() {
        return referCode;
    }

    @JsonBackReference(value ="favorite")
    public List<FavoriteBusiness>  getFavoriteBusinesses() {
        return favoriteBusinesses;
    }


}
