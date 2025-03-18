package utez.edu.mx.communitycommitteesystem.model.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.sms.SmsBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;

@Entity
@Getter
@Setter
@Table(name = "person")
public class PersonBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String lastname;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String phone;

    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StateBean stateBean;

    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MunicipalityBean municipalityBean;

    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ColonyBean colonyBean;

    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CommitteeBean committeeBean;

    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AreaBean areaBean;

    @OneToOne(mappedBy = "personBean", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SmsBean smsBean;


}
