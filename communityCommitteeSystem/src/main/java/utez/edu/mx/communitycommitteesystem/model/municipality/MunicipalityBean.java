package utez.edu.mx.communitycommitteesystem.model.municipality;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "municipality")
public class MunicipalityBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nameMunicipality;

    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonBean personBean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idState")
    private StateBean stateBean;

    @OneToMany( mappedBy = "municipalityBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColonyBean> colonyBeanList;

    @OneToMany( mappedBy = "municipalityBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AreaBean> areaBeanList;

    @OneToMany(mappedBy = "municipalityBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;
}
