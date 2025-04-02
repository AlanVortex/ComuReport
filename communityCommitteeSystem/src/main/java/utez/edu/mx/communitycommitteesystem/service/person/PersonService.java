package utez.edu.mx.communitycommitteesystem.service.person;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonRepository;

@Service
@Transactional
@Getter

public class PersonService {

    private final PersonRepository personRepository;



    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    // Buscar persona por correo
    public PersonBean findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    // Guardar persona
    public PersonBean save(PersonBean person) {
        person.setStatus(true);
        person.setBlocked(false);
        return personRepository.save(person);

    }

    public PersonBean saveMun(PersonBean person) {
        person.setStatus(true);
        person.setBlocked(false);
        return personRepository.save(person);
    }



    public PersonBean saveColony(PersonBean person) {
        person.setStatus(true);
        person.setBlocked(false);
        return personRepository.save(person);
    }

    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}

