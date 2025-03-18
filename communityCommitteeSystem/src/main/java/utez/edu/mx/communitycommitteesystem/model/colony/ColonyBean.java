package utez.edu.mx.communitycommitteesystem.model.colony;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;

import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonBean personBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMunicipality")
    private MunicipalityBean municipalityBean;

    @OneToMany( mappedBy = "colonyBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommitteeBean> committeeBeanList;

    @OneToMany( mappedBy = "colonyBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;
}
