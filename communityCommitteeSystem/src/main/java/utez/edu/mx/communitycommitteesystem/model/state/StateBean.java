package utez.edu.mx.communitycommitteesystem.model.state;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

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
}
