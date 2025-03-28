package utez.edu.mx.communitycommitteesystem.service.municipality;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.controller.municipality.AssignAdminMunicipalityDto;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityRepository;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipalityService {
    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private StateService stateService;

    @Autowired
    private PersonService personService;

    public Optional<MunicipalityBean> findById(Long id) {
        return municipalityRepository.findById(id);
    }

    public MunicipalityBean save(MunicipalityBean municipality) {
        return municipalityRepository.save(municipality);
    }

    public Optional<MunicipalityBean> findByUuid(String uuid) {
        return Optional.ofNullable(municipalityRepository.findByUuid(uuid));
    }

    public String registerMunicipalityWithAdmin(AssignAdminMunicipalityDto dto) {
        StateBean state = stateService.findByUuid(dto.getStateUuid());
        if (state == null) {
            throw new EntityNotFoundException("No se encontró un estado con el UUID proporcionado.");
        }

        PersonBean person = new PersonBean();
        person.setName(dto.getName());
        person.setLastname(dto.getLastname());
        person.setEmail(dto.getEmail());

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPsw = bcrypt.encode(dto.getPassword());
        person.setPassword(encryptedPsw);
        person.setPhone(dto.getPhone());

        PersonBean savedPerson = personService.saveMun(person);

        MunicipalityBean municipality = new MunicipalityBean();
        municipality.setNameMunicipality(dto.getMunicipalityName());
        municipality.setPersonBean(savedPerson);
        municipality.setStateBean(state);

        save(municipality);

        return "Municipio y administrador registrados correctamente";
    }

    public List<MunicipalityBean> getMunicipalitiesByStateUuid(String stateUuid) {
        StateBean state = stateService.findByUuid(stateUuid);
        if (state == null) {
            throw new EntityNotFoundException("Estado no encontrado con el UUID proporcionado.");
        }
        return state.getMunicipalityBeanList();
    }

    public PersonBean getMunicipalityAdminByUuid(String municipalityUuid) {
        MunicipalityBean municipality = municipalityRepository.findByUuid(municipalityUuid);
        if (municipality == null) {
            throw new EntityNotFoundException("Municipio no encontrado con el UUID proporcionado.");
        }
        return municipality.getPersonBean(); // Obtienes la persona (administrador) asociada
    }


}
