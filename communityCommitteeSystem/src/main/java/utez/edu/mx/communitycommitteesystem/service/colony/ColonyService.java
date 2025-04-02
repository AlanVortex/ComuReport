package utez.edu.mx.communitycommitteesystem.service.colony;

import jakarta.persistence.EntityNotFoundException;
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
        return colonyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Colony not found"));
    }

    public void delete(String uuidMunicipality ,String uuid) {

        MunicipalityBean municipalityBean = municipalityService.findByUuid(uuidMunicipality).orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        ColonyBean colonyBean =  colonyRepository.findByUuidAndMunicipalityBean(uuid, municipalityBean).orElseThrow(() -> new EntityNotFoundException("Colony not found"));

        colonyRepository.delete(colonyBean);
    }

    public ColonyBean get(String uuid , String uuidMunicipality) {
        MunicipalityBean municipalityBean = municipalityService.findByUuid(uuidMunicipality).orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        ColonyBean colonyBean =  colonyRepository.findByUuidAndMunicipalityBean(uuid, municipalityBean).orElseThrow(() -> new EntityNotFoundException("Colony not found"));
        return colonyBean;
    }

    public List<ColonyBean> findByMunicipality(MunicipalityBean municipality) {
        return colonyRepository.findByMunicipalityBean(municipality);
    }

    public String registerColonyWithLink(ColonyBean colonyBean , String uuid) {
        PersonBean savedPerson = personService.saveColony(colonyBean.getPersonBean());
        colonyBean.setPersonBean(savedPerson);
        MunicipalityBean municipalityBean = municipalityService.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        colonyBean.setMunicipalityBean(municipalityBean);
        colonyRepository.save(colonyBean);

        return "Colony Success";
    }

    public List<ColonyBean> findAll(String uuidMunicipality) {
       MunicipalityBean  municipalityBean   =  municipalityService.findByUuid(uuidMunicipality).orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        return colonyRepository.findByMunicipalityBean(municipalityBean);
    }
}