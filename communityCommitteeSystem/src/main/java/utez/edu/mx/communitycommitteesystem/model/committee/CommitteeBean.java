package utez.edu.mx.communitycommitteesystem.model.committee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
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

    @Column(length = 100)
    private String nameCommittee;


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


    public PersonBean getPersonBean() {
        return personBean;
    }


}

