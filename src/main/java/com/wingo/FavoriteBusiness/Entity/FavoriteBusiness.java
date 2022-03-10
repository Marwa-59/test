package com.wingo.FavoriteBusiness.Entity;

import com.wingo.Business.Entity.Business;
import com.wingo.WingoUser.Entity.WingoUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "favoriteBusiness")
public class FavoriteBusiness {
    @Id
    @Column(name = "favorite_business_id", updatable = false)
    private String favorite_business_id = UUID.randomUUID().toString();

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = true)
    private WingoUser user;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "business_id", nullable = true)
    private Business business;

}
