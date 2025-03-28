package utez.edu.mx.communitycommitteesystem.service.colony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.controller.colony.ColonyWithLinkDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyRepository;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import java.util.List;
import java.util.Optional;

@Service
public class ColonyService {
    @Autowired
    private ColonyRepository colonyRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private MunicipalityService municipalityService;

    public ColonyBean findById(Long id) {
        return colonyRepository.findById(id).orElseThrow(() -> new RuntimeException("Colony not found"));
    }


    public Optional<ColonyBean> findByUuid(String uuid) {
        return colonyRepository.findByUuid(uuid);
    }

    public List<ColonyBean> findByMunicipality(MunicipalityBean municipality) {
        return colonyRepository.findByMunicipalityBean(municipality);
    }

    public void registerColonyWithLink(ColonyWithLinkDto dto) {
        PersonBean person = new PersonBean();
        person.setName(dto.getName());
        person.setLastname(dto.getLastname());
        person.setEmail(dto.getEmail());

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPsw = bcrypt.encode(dto.getPassword());
        person.setPassword(encryptedPsw);
        person.setPhone(dto.getPhone());

        PersonBean savedPerson = personService.saveColony(person);

        MunicipalityBean municipality = municipalityService.findByUuid(dto.getMunicipalityUuid())
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado."));

        // Crear la colonia
        ColonyBean colony = new ColonyBean();
        colony.setNameColony(dto.getColonyName());
        colony.setMunicipalityBean(municipality);
        colony.setPersonBean(savedPerson);

        save(colony);
    }

    public void save(ColonyBean colony) {
         colonyRepository.save(colony);
    }

}