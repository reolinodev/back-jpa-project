package com.back.domain.sample;

import com.back.domain.common.listener.UserHistoryListener;
import com.back.domain.sample.common.BaseEntity;
import com.back.domain.sample.params.UserParam;
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
@Table(name="TB_USER", schema = "sample")
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

    public String useYn;

    @ColumnDefault("0")
    public int loginFailCnt;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "home_city")),
        @AttributeOverride(name = "district", column = @Column(name = "home_district")),
        @AttributeOverride(name = "addressDetail", column = @Column(name = "home_address_detail")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "home_zip_code"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "company_city")),
        @AttributeOverride(name = "district", column = @Column(name = "company_district")),
        @AttributeOverride(name = "addressDetail", column = @Column(name = "company_address_detail")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "company_zip_code"))
    })
    private Address companyAddress;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<LoginHistory> loginHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Rental> rentals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptCd", referencedColumnName = "deptCd")
    @ToString.Exclude
    public Dept dept;

    public void setUser(UserParam userParam) {
        this.userNm = userParam.userNm;
        this.loginId = userParam.loginId;
        this.userPw = userParam.userPw;
        this.telNo = userParam.telNo;
        this.useYn = userParam.useYn;
        this.homeAddress.city = userParam.homeCity;
        this.homeAddress.district = userParam.homeDistrict;
        this.homeAddress.addressDetail = userParam.homeAddressDetail;
        this.homeAddress.zipCode = userParam.homeZipCode;
        this.companyAddress.city = userParam.companyCity;
        this.companyAddress.district = userParam.companyDistrict;
        this.companyAddress.addressDetail = userParam.companyAddressDetail;
        this.companyAddress.zipCode = userParam.companyZipCode;
    }

}
