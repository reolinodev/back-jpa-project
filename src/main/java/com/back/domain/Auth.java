package com.back.domain;


import com.back.domain.common.BaseEntity;
import com.back.domain.params.AuthParam;
import com.back.domain.params.UserParam;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_AUTH", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//권한정보
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //권한명
    public String authNm;

    //권한코드
    public String authCd;

    //권한구분
    public String authRole;

    //순서
    public String ord;

    //비고
    public String memo;

    public void setCreateParam(AuthParam authParam) {
        this.authNm = authParam.authNm;
        this.authCd = authParam.authCd;
        this.authRole = authParam.authRole;
        this.ord = authParam.ord;
        this.memo = authParam.memo;
        this.createdId = authParam.createdId;
        this.useYn = "Y";
    }

    public void setUpdateParam(AuthParam authParam) {

        this.updatedId = authParam.updatedId;

        if(authParam.authNm != null){
            this.authNm = authParam.authNm;
        }

        if(authParam.ord != null){
            this.ord = authParam.ord;
        }

        if(authParam.memo != null){
            this.memo = authParam.memo;
        }

        if(authParam.useYn != null){
            this.useYn = authParam.useYn;
        }
    }
}
