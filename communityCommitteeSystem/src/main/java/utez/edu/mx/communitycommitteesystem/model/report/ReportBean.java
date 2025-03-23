package utez.edu.mx.communitycommitteesystem.model.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.image.ImageBean;
import utez.edu.mx.communitycommitteesystem.model.sms.SmsBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;

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
    @Column(length = 100, nullable = false)
    private String statusDescription;

    @ManyToOne(fetch = FetchType.LAZY)
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

}
