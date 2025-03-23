package utez.edu.mx.communitycommitteesystem.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    // Buscar persona por correo
    public PersonBean findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    // Guardar persona
    public void save(PersonBean person) {
        personRepository.save(person);
    }

    public PersonBean saveMun(PersonBean person) {
        return personRepository.save(person);
    }

    public PersonBean saveState(PersonBean person) {
        return personRepository.save(person);
    }

    public PersonBean saveColony(PersonBean person) {
        return personRepository.save(person);
    }
}

