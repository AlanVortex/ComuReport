package utez.edu.mx.communitycommitteesystem.model.colony;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "colony")

public class ColonyBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nameColony;

    @Column(length = 36, nullable = false, unique = true, updatable = false)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonBean personBean;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMunicipality")
    private MunicipalityBean municipalityBean;

    @OneToMany(mappedBy = "colonyBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommitteeBean> committeeBeanList;

    @OneToMany(mappedBy = "colonyBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;



    public ColonyBean() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ColonyBean(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameColony() {
        return nameColony;
    }

    public void setNameColony(String nameColony) {
        this.nameColony = nameColony;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public MunicipalityBean getMunicipalityBean() {
        return municipalityBean;
    }

    public void setMunicipalityBean(MunicipalityBean municipalityBean) {
        this.municipalityBean = municipalityBean;
    }

    public List<CommitteeBean> getCommitteeBeanList() {
        return committeeBeanList;
    }

    public void setCommitteeBeanList(List<CommitteeBean> committeeBeanList) {
        this.committeeBeanList = committeeBeanList;
    }

    public List<ReportBean> getReportBeanList() {
        return reportBeanList;
    }

    public void setReportBeanList(List<ReportBean> reportBeanList) {
        this.reportBeanList = reportBeanList;
    }
}
