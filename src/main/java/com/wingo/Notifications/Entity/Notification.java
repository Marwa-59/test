package com.wingo.Notifications.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "notification")
public class Notification {
    @Id
    @Column(name = "notification_id", updatable = false)
    private String notificationId = UUID.randomUUID().toString();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
