package utez.edu.mx.communitycommitteesystem.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.sms.SmsBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;

import java.util.List;

@Entity
@Data
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
            return colonyBean.getUuid();
        }
        if ( this.stateBean!=null){
            return stateBean.getUuid();
        }
        return "";
    }
}
