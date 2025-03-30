package utez.edu.mx.communitycommitteesystem.model.municipality;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@Table(name = "municipality")
public class MunicipalityBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nameMunicipality;

    @Column(length = 36, nullable = false, unique = true, updatable = false)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonBean personBean;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idState")
    private StateBean stateBean;

    @JsonIgnore
    @OneToMany( mappedBy = "municipalityBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColonyBean> colonyBeanList;

    @OneToMany( mappedBy = "municipalityBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AreaBean> areaBeanList;

    @OneToMany(mappedBy = "municipalityBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;


    public MunicipalityBean() {
        this.uuid = UUID.randomUUID().toString();
    }


    public String getUuid() {
        return uuid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameMunicipality() {
        return nameMunicipality;
    }

    public void setNameMunicipality(String nameMunicipality) {
        this.nameMunicipality = nameMunicipality;
    }

    public StateBean getStateBean() {
        return stateBean;
    }

    public void setStateBean(StateBean stateBean) {
        this.stateBean = stateBean;
    }

    public List<ColonyBean> getColonyBeanList() {
        return colonyBeanList;
    }

    public void setColonyBeanList(List<ColonyBean> colonyBeanList) {
        this.colonyBeanList = colonyBeanList;
    }

    public List<AreaBean> getAreaBeanList() {
        return areaBeanList;
    }

    public void setAreaBeanList(List<AreaBean> areaBeanList) {
        this.areaBeanList = areaBeanList;
    }

    public List<ReportBean> getReportBeanList() {
        return reportBeanList;
    }

    public void setReportBeanList(List<ReportBean> reportBeanList) {
        this.reportBeanList = reportBeanList;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

}
