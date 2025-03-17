package utez.edu.mx.communitycommitteesystem.model.person;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PersonBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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
}
