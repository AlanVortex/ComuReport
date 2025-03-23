package utez.edu.mx.communitycommitteesystem.model.state;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

import java.util.List;

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
    private PersonBean personBean;

    @OneToMany( mappedBy = "stateBean",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MunicipalityBean> municipalityBeanList;

    public StateBean() {
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
