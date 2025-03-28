package utez.edu.mx.communitycommitteesystem.model.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.image.ImageBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.sms.SmsBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "report")

public class ReportBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 250, nullable = false)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;
    @Column(length = 100)
    private String statusDescription;
    @Column(length = 36, nullable = false, unique = true, updatable = false)
    private String uuid;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idColony")
    private ColonyBean colonyBean;

    @OneToMany( mappedBy = "reportBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SmsBean> smsBeanList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArea")
    private AreaBean areaBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatus")
    private StatusBean statusBean;

    @OneToMany( mappedBy = "reportBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageBean> ImageBeanList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCommittee")
    private CommitteeBean committeeBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMunicipality")
    private MunicipalityBean municipalityBean;

    public ReportBean() {
        this.uuid = java.util.UUID.randomUUID().toString();
        this.ImageBeanList = new ArrayList<>();

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public ColonyBean getColonyBean() {
        return colonyBean;
    }

    public void setColonyBean(ColonyBean colonyBean) {
        this.colonyBean = colonyBean;
    }

    public List<SmsBean> getSmsBeanList() {
        return smsBeanList;
    }

    public void setSmsBeanList(List<SmsBean> smsBeanList) {
        this.smsBeanList = smsBeanList;
    }

    public AreaBean getAreaBean() {
        return areaBean;
    }

    public void setAreaBean(AreaBean areaBean) {
        this.areaBean = areaBean;
    }

    public StatusBean getStatusBean() {
        return statusBean;
    }

    public void setStatusBean(StatusBean statusBean) {
        this.statusBean = statusBean;
    }

    public List<ImageBean> getImageBeanList() {
        return ImageBeanList;
    }

    public void setImageBeanList(List<ImageBean> imageBeanList) {
        ImageBeanList = imageBeanList;
    }

    public CommitteeBean getCommitteeBean() {
        return committeeBean;
    }

    public void setCommitteeBean(CommitteeBean committeeBean) {
        this.committeeBean = committeeBean;
    }

    public MunicipalityBean getMunicipalityBean() {
        return municipalityBean;
    }

    public void setMunicipalityBean(MunicipalityBean municipalityBean) {
        this.municipalityBean = municipalityBean;
    }

    public PersonBean getPersonBean() {
        return colonyBean.getPersonBean();
    }
}
