package utez.edu.mx.communitycommitteesystem.model.committee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "committee")
public class CommitteeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idColony")
    private ColonyBean colonyBean;

    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonBean personBean;

    @OneToMany( mappedBy = "committeeBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;

}
