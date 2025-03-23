package utez.edu.mx.communitycommitteesystem.model.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonBean,Long> {
    PersonBean findByEmail(String email);

}
