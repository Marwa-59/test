package com.wingo.Rate.Entity;

import com.wingo.WingoUser.Entity.WingoUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rate")
public class Rate {

    @Id
    @Column(name = "rate_id", updatable = false)
    private String rate_id = UUID.randomUUID().toString();

    @Column(name = "rate_number")
    private Integer rateNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rate", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<WingoUser> wingoUsers = new ArrayList<>();
}
