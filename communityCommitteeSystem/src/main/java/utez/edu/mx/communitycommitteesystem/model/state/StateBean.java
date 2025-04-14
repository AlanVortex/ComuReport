package utez.edu.mx.communitycommitteesystem.model.state;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "state")
public class StateBean {
    @JsonIgnore
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


    @OneToMany( mappedBy = "stateBean",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<MunicipalityBean> municipalityBeanList;

    @JoinColumn(name = "status", columnDefinition = "true")
    private boolean status;
    public StateBean() {
        this.uuid = UUID.randomUUID().toString(); // Generar UUID al crear la entidad
    }





}
