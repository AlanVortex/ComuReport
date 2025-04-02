package utez.edu.mx.communitycommitteesystem.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.sms.SmsBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "person",indexes = {
        @Index(name = "idx_unique_email", columnList = "email", unique = true)
})
public class PersonBean {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String lastname;
    @Column(length = 50, nullable = false)
    private String email;
    @Column( nullable = false)
    @JsonIgnore
    private String password;
    @Column(length = 50, nullable = false)
    private String phone;
    @JsonIgnore
    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StateBean stateBean;
    @JsonIgnore
    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MunicipalityBean municipalityBean;
    @JsonIgnore
    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ColonyBean colonyBean;
    @JsonIgnore
    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CommitteeBean committeeBean;
    @JsonIgnore
    @OneToOne(mappedBy = "personBean", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private AreaBean areaBean;
    @JsonIgnore
    @OneToMany(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SmsBean> smsBeanList;
    @JsonIgnore
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status;
    @JsonIgnore
    @Column(columnDefinition = "BOOL DEFAULT false")
    private Boolean blocked;
    @JsonIgnore
    private String token;


    public PersonBean() {
    }

    public PersonBean(Long id, String name, String lastname, String email, String password, String phone, StateBean stateBean, MunicipalityBean municipalityBean, ColonyBean colonyBean, CommitteeBean committeeBean, AreaBean areaBean, List<SmsBean> smsBeanList) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.stateBean = stateBean;
        this.municipalityBean = municipalityBean;
        this.colonyBean = colonyBean;
        this.committeeBean = committeeBean;
        this.areaBean = areaBean;
        this.smsBeanList = smsBeanList;
    }

    public PersonBean(Long id, String name, String lastname, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @JsonIgnore
    public String getRole(){
        if(this.committeeBean!=null){
            return "Committee";
        }
        if(this.areaBean!=null){
            return "Area";
        }
        if ( this.municipalityBean!=null){
            return "Municipality";
        }
        if ( this.colonyBean!=null){
            return "Colony";
        }
        if ( this.stateBean!=null){
            return "State";
        }
        return "";
    }
    @JsonIgnore
    public String getRoleUuid(){
        if(this.committeeBean!=null){
            return committeeBean.getUuid();
        }
        if(this.areaBean!=null){
            return areaBean.getUuid();
        }
        if ( this.municipalityBean!=null){
            return municipalityBean.getUuid();
        }
        if ( this.colonyBean!=null){
            return committeeBean.getUuid();
        }
        if ( this.stateBean!=null){
            return stateBean.getUuid();
        }
        return "";
    }
}
