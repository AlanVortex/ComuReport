package utez.edu.mx.communitycommitteesystem.service.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(PersonBean person) {
        personRepository.save(person);
    }

    public PersonBean saveMun(PersonBean person) {
        person.setStatus(true);
        person.setBlocked(false);
        return personRepository.save(person);
    }

    public PersonBean saveState(PersonBean person) {
        person.setStatus(true);
        person.setBlocked(false);
        return personRepository.save(person);
    }

    public PersonBean saveColony(PersonBean person) {
        person.setStatus(true);
        person.setBlocked(false);
        return personRepository.save(person);
    }
}

