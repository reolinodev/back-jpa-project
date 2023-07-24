package com.back.domain;

import com.back.domain.params.LoginParam;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_LOGIN_HISTORY", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode()
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //접속디바이스
    public String loginDevice;

    //디바이스 브라우저
    public String deviceBrowser;


    //억세스토큰
    public String accessToken;

    //리프레시토큰
    public String refreshToken;

    @Column(updatable = false)
    @CreatedDate
    public LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public User user;

    public void setCreateParam(LoginParam loginParam) {
        this.loginDevice = loginParam.loginDevice;
        this.deviceBrowser = loginParam.deviceBrowser;
        this.accessToken = loginParam.accessToken;
        this.refreshToken = loginParam.refreshToken;
    }
}
