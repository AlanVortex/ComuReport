package utez.edu.mx.communitycommitteesystem.service.state;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.controller.state.StateWithAdminDto;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateRepository;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import java.util.List;
import java.util.Optional;

@Service
@Data

public class StateService {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private PersonService personService;

    private BCryptPasswordEncoder bcryptPasswordEncoder;


    public void save(StateBean state) {
        stateRepository.save(state);
    }

    public StateBean findByUuid(String stateUuid) {
        return stateRepository.findByUuid(stateUuid).orElse(null);
    }

    public List<StateBean> findByNameState(String nameState) {
        return stateRepository.findByNameState(nameState);
    }

    // Si quieres obtener los estados que están asociados a un municipio en particular
    public List<StateBean> findByMunicipality(MunicipalityBean municipalityBean) {
        return stateRepository.findByMunicipalityBeanList(municipalityBean);
    }

    public String registerStateWithAdmin(StateWithAdminDto dto) {
        PersonBean person = new PersonBean();
        person.setName(dto.getName());
        person.setLastname(dto.getLastname());
        person.setEmail(dto.getEmail());

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPsw = bcrypt.encode(dto.getPassword());
        person.setPassword(encryptedPsw);
        person.setPhone(dto.getPhone());

        PersonBean savedPerson = personService.saveState(person);

        StateBean state = new StateBean();
        state.setNameState(dto.getStateName());
        state.setPersonBean(savedPerson);

        stateRepository.save(state);

        return "Estado y administrador registrados correctamente";
    }

    public List<StateBean> findStateAdminsByUuid(String stateUuid) {
        // Buscar el estado por su UUID
        StateBean state = stateRepository.findByUuid(stateUuid).orElse(null);

        if (state == null) {
            throw new RuntimeException("Estado no encontrado");
        }

        return stateRepository.findByNameState(state.getNameState());
    }

    public StateBean findStateAdminByUuid(String adminUuid) {
        return stateRepository.findByUuid(adminUuid).orElseThrow(() ->
                new RuntimeException("Administrador de estado no encontrado"));
    }
}
