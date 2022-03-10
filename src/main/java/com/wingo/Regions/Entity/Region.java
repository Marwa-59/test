package com.wingo.Regions.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wingo.Branches.Entity.Branches;
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
@Table(name = "region")
public class Region {

    @Id
    @Column(name = "region_id", updatable = false)
    private String regionId = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Branches> branches = new ArrayList<>();

    @JsonBackReference
    public List<Branches> getBranches() {
        return branches;
    }
}
