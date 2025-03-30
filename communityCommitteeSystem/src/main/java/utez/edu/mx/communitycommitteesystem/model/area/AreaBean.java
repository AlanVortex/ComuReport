package utez.edu.mx.communitycommitteesystem.model.area;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "area")
public class AreaBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nameArea;
    @Column(length = 36, nullable = false, unique = true, updatable = false)
    private String uuid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMunicipality")
    private MunicipalityBean municipalityBean;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idPerson"  )
    private PersonBean personBean;


    @OneToMany( mappedBy = "areaBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportBean> reportBeanList;

    public AreaBean() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
