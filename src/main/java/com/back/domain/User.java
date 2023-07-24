package com.back.domain;

import com.back.domain.common.BaseEntity;
import com.back.domain.listener.UserHistoryListener;
import com.back.domain.params.UserParam;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners({UserHistoryListener.class})
@Table(name="TB_USER", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String userNm;

    @Column(nullable = false, updatable = false)
    public String loginId;

    @ToString.Exclude
    public String userPw;

    public String telNo;

    @ColumnDefault("0")
    public int loginFailCnt;

    //패스초기화여부
    @ColumnDefault("'N'")
    public String pwInitYn;

    //최근 로그인시간
    public LocalDateTime lastLoginAt;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    public List<LoginHistory> loginHistories = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    public List<UserHistory> userHistories = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private List<Review> reviews = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private List<Rental> rentals = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "deptCd", referencedColumnName = "deptCd")
//    @ToString.Exclude
//    public Dept dept;

    public void setCreateParam(UserParam userParam) throws NoSuchAlgorithmException {
        this.userNm = userParam.userNm;
        this.loginId = userParam.loginId;
        this.userPw = CryptUtils.encryptSha256(userParam.userPw);
        this.telNo = userParam.telNo;
        this.useYn = userParam.useYn;
    }


    public void setUpdateParam(UserParam userParam) throws NoSuchAlgorithmException {
        if(userParam.userNm != null){
            this.userNm = userParam.userNm;
        }

        if(userParam.userPw != null) {
            this.userPw = CryptUtils.encryptSha256(userParam.userPw);
        }

        if(userParam.telNo != null){
            this.telNo = userParam.telNo;
        }

        if(userParam.useYn != null){
            this.useYn = userParam.useYn;
        }
    }
}
