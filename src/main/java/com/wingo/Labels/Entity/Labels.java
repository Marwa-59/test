package com.wingo.Labels.Entity;

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
@Table(name = "labels")
public class Labels {

    @Id
    @Column(name = "labels_id", updatable = false)
    private String labels_id = UUID.randomUUID().toString();

    @Column(name = "privacy_policy")
    private String privacyPolicy;

    @Column(name = "about_us")
    private String aboutUs;
}
