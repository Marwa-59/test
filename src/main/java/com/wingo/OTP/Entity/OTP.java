package com.wingo.OTP.Entity;

import com.wingo.WingoUser.Entity.WingoUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class OTP {
    @Id
    @Column(name = "otp_id", updatable = false)
    private String otp_id = UUID.randomUUID().toString();
    private String value;
    @Column(nullable = false)
    private Date expiryDate;
    @OneToOne(targetEntity = WingoUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private WingoUser user;

    public void setExpiryDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
    }
}
