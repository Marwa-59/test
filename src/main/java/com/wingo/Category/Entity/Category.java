package com.wingo.Category.Entity;

import com.wingo.Business.Entity.Business;
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
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "category_id", updatable = false)
    private String categoryId = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Business> businesses = new ArrayList<>();
}
