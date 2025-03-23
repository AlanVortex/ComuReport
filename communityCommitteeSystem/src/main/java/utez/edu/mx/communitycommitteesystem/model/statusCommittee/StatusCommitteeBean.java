package utez.edu.mx.communitycommitteesystem.model.statusCommittee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "statusCommittee")
public class StatusCommitteeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String type;

    @OneToMany( mappedBy = "statusCommitteeBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommitteeBean> committeeBeanList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CommitteeBean> getCommitteeBeanList() {
        return committeeBeanList;
    }

    public void setCommitteeBeanList(List<CommitteeBean> committeeBeanList) {
        this.committeeBeanList = committeeBeanList;
    }
}
