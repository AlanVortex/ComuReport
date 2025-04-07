package utez.edu.mx.communitycommitteesystem.service.colony;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyRepository;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import java.util.List;

@Service
@AllArgsConstructor
public class ColonyService {

    private final ColonyRepository colonyRepository;


    private final PersonService personService;


    private final MunicipalityService municipalityService;

    public ColonyBean findByUuid(MunicipalityBean municipalityBean, String uuid) {
        return colonyRepository.findByUuidAndMunicipalityBean(uuid, municipalityBean).orElseThrow(() -> new EntityNotFoundException("Colony not found"));
    }
    public ColonyBean findByUuid( String uuid) {
        return colonyRepository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Colony not found"));
    }

    public boolean delete(String uuidMunicipality, String uuid) {

        personService.delete(get(uuid,uuidMunicipality).getPersonBean());
        return true;
    }

    public ColonyBean get(String uuid, String uuidMunicipality) {
        return findByUuid(municipalityService.findByUuid(uuidMunicipality) ,uuid);
    }


    public String registerColonyWithLink(ColonyBean colonyBean, String uuid) {
        PersonBean savedPerson = personService.saveColony(colonyBean.getPersonBean());
        colonyBean.setPersonBean(savedPerson);
        MunicipalityBean municipalityBean = municipalityService.findByUuid(uuid);
        colonyBean.setMunicipalityBean(municipalityBean);
        colonyRepository.save(colonyBean);

        return "Colony Success";
    }

    public List<ColonyBean> findAll(String uuidMunicipality) {
        return colonyRepository.findByMunicipalityBean(municipalityService.findByUuid(uuidMunicipality));
    }
}