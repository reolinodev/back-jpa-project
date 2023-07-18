package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import com.back.domain.sample.params.DeptParam;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="TB_DEPT", schema = "sample")
public class Dept extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String deptNm;

    @Column(nullable = false, updatable = false)
    public String deptCd;

    public String upperDeptCd;

    @ColumnDefault("'Y'")
    public String useYn;

    @OneToMany(mappedBy = "dept")
    @ToString.Exclude
    public List<User> users;

    public void setDept(DeptParam deptParam) {
        this.deptNm = deptParam.deptNm;
        this.deptCd = deptParam.deptCd;
        this.upperDeptCd = deptParam.upperDeptCd;
        this.useYn = deptParam.useYn;
    }
}
