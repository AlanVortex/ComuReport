package utez.edu.mx.communitycommitteesystem.model.status;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "status")
public class StatusBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String type;
    @Column(length = 100, nullable = false)
    private String category;

    @OneToMany( mappedBy = "statusBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;

    @OneToMany( mappedBy = "statusBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommitteeBean> committeeBeanList;

    public StatusBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
