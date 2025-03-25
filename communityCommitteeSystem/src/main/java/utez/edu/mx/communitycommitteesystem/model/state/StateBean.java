package utez.edu.mx.communitycommitteesystem.model.state;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "state")
public class StateBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nameState;

    @OneToOne
    @JoinColumn(name = "idPerson")
    @JsonManagedReference

    private PersonBean personBean;

    @Column(length = 36, nullable = false, unique = true, updatable = false)
    private String uuid;


    @OneToMany( mappedBy = "stateBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MunicipalityBean> municipalityBeanList;

    public StateBean() {
        this.uuid = UUID.randomUUID().toString(); // Generar UUID al crear la entidad
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameState() {
        return nameState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    public List<MunicipalityBean> getMunicipalityBeanList() {
        return municipalityBeanList;
    }

    public void setMunicipalityBeanList(List<MunicipalityBean> municipalityBeanList) {
        this.municipalityBeanList = municipalityBeanList;
    }

}
