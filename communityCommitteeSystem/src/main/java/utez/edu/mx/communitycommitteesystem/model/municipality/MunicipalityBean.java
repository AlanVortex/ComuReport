package utez.edu.mx.communitycommitteesystem.model.municipality;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

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


}
