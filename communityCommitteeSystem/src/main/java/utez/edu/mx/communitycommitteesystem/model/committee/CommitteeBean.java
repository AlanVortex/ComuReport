package utez.edu.mx.communitycommitteesystem.model.committee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteeBean;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "committee")
public class CommitteeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, unique = true, nullable = false)
    private String uuid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idColony")
    private ColonyBean colonyBean;

    @Getter
    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonBean personBean;

    @OneToMany( mappedBy = "committeeBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatus")
    private StatusCommitteeBean statusCommitteeBean;

    public CommitteeBean() {
        this.uuid = UUID.randomUUID().toString(); // Generar UUID automáticamente
    }
    public CommitteeBean(Long id, ColonyBean colonyBean, PersonBean personBean, List<ReportBean> reportBeanList) {
        this.id = id;
        this.colonyBean = colonyBean;
        this.personBean = personBean;
        this.reportBeanList = reportBeanList;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }
    public void setColonyBean(ColonyBean colonyBean) {
        this.colonyBean = colonyBean;
    }
    public void setStatusCommitteeBean(StatusCommitteeBean statusCommitteeBean) {
        this.statusCommitteeBean = statusCommitteeBean;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}

