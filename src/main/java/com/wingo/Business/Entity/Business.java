package com.wingo.Business.Entity;

import com.wingo.Category.Entity.Category;
import com.wingo.FavoriteBusiness.Entity.FavoriteBusiness;
import com.wingo.Branches.Entity.Branches;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Entity
@Data
@NoArgsConstructor
@Table(name = "business")
public class Business {

    @Id
    @Column(name = "business_id", updatable = false)
    private String businessId = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "is_hot_offers")
    private Boolean isHotOffers;

    @Column(name = "image")
    private String image;

    @Column(name = "instagram_link")
    private String instagramLink;

    @Column(name = "menu")
    private String menu;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "website")
    private String website;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<FavoriteBusiness> favoriteBusinesses = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Branches> branches = new ArrayList<>();

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<WingoUser> wingoUsers = new ArrayList<>();

    @JsonBackReference(value ="favorite")
    public List<FavoriteBusiness> getFavoriteBusinesses() {
        return favoriteBusinesses;
    }

    @JsonBackReference(value="business-category")
    public Category getCategory() {
        return category;
    }
}
