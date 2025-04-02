package utez.edu.mx.communitycommitteesystem.service.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
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

    public String registerStateWithAdmin(StateBean stateBean) {



        PersonBean personBean =  personService.save(stateBean.getPersonBean());
        stateBean.setPersonBean(personBean);


        stateRepository.save(stateBean);
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
