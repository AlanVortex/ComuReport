package utez.edu.mx.communitycommitteesystem.service.state;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateRepository;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import java.util.List;

@Service
@Data

public class StateService {

    private final StateRepository stateRepository;


    private final PersonService personService;

    private  BCryptPasswordEncoder bcryptPasswordEncoder;



    public String registerStateWithAdmin(StateBean stateBean) {
        PersonBean personBean =  personService.save(stateBean.getPersonBean());
        stateBean.setPersonBean(personBean);
        stateRepository.save(stateBean);
        return "Estado y administrador registrados correctamente";
    }

    public StateBean findByUuid(String stateUuid) {
        // Buscar el estado por su UUID
       return stateRepository.findByUuid(stateUuid).orElseThrow(() -> new EntityNotFoundException("Not fount state"));
    }

    public StateBean update(StateBean stateBean) {
        StateBean state = findByUuid(stateBean.getUuid());
        state.getPersonBean().setPhone(stateBean.getPersonBean().getPhone());
        state.getPersonBean().setEmail(stateBean.getPersonBean().getEmail());
        return stateRepository.save(stateBean);
    }



}
