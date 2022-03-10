package com.wingo.Inbox.Entity;


import com.wingo.WingoUser.Entity.WingoUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "inbox")
public class Inbox {

    @Id
    @Column(name = "inbox_id", updatable = false)
    private String inboxId = UUID.randomUUID().toString();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<WingoUser> wingoUsers = new ArrayList<>();
}
