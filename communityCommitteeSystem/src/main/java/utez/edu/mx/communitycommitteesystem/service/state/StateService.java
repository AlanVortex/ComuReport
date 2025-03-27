package utez.edu.mx.communitycommitteesystem.service.state;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data

public class StateService {
    @Autowired
    private StateRepository stateRepository;


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


}
