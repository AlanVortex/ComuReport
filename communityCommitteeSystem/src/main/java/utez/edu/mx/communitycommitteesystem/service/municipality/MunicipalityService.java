package utez.edu.mx.communitycommitteesystem.service.municipality;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityRepository;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MunicipalityService {
    private final MunicipalityRepository municipalityRepository;


    private final StateService stateService;


    private final PersonService personService;

    public MunicipalityBean findByUuid(String uuid) {
        return municipalityRepository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Municipality not found!."));
    }

    public String registerMunicipalityWithAdmin(MunicipalityBean municipalityBean , String uuidState) {
        StateBean state = stateService.findByUuid(uuidState);
        if (state == null) {
            throw new EntityNotFoundException("No se encontró un estado con el UUID proporcionado.");
        }

        PersonBean savedPerson = personService.save(municipalityBean.getPersonBean());
        municipalityBean.setPersonBean(savedPerson);
        municipalityBean.setStateBean(state);
        municipalityRepository.save(municipalityBean);

        return "Municipio y administrador registrados correctamente";
    }

    public List<MunicipalityBean> getMunicipalitiesByStateUuid(String stateUuid) {
        StateBean state = stateService.findByUuid(stateUuid);
        if (state == null) {
            throw new EntityNotFoundException("Estado no encontrado con el UUID proporcionado.");
        }
        return state.getMunicipalityBeanList();
    }

    public MunicipalityBean getMunicipalityAdminByUuid(String municipalityUuid , String uuidState) {
        MunicipalityBean municipality = findByUuid(municipalityUuid);

        municipalityRepository.findByUuidAndStateBean(uuidState,municipality.getStateBean());
        return municipality; // Obtienes la persona (administrador) asociada
    }
    public MunicipalityBean update (MunicipalityBean municipalityBean , String uuidState) {
        MunicipalityBean municipalityUpdate = getMunicipalityAdminByUuid(municipalityBean.getUuid(), uuidState);

        municipalityUpdate.setNameMunicipality(municipalityBean.getNameMunicipality());

        return  municipalityRepository.save(municipalityUpdate);
    }

    public void  delete(MunicipalityBean municipalityBean , String uuidState) {
        MunicipalityBean municipality = getMunicipalityAdminByUuid(municipalityBean.getUuid(), uuidState);

        municipalityRepository.delete(municipality);
    }


}
