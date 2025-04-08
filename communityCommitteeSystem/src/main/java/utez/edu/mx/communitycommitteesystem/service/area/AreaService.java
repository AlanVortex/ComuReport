package utez.edu.mx.communitycommitteesystem.service.area;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.exception.GlobalExceptionHandler;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.area.AreaRepository;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import java.util.List;

@AllArgsConstructor
@Service
public class AreaService {


    private final AreaRepository areaRepository;

    private final PersonService personService;


    private final MunicipalityService municipalityService;

    private static final Logger logger = LogManager.getLogger(AreaService.class);

    public AreaBean getArea(String areaUuid, String municipalityUuid) {
        MunicipalityBean municipalityBean = municipalityService.findByUuid(municipalityUuid);

        AreaBean areaBaen = areaRepository.findByUuidAndMunicipalityBean(areaUuid, municipalityBean)
                .orElseThrow(() -> new EntityNotFoundException("Area not found"));
        return  areaBaen;
    }

    public List<AreaBean> findByMunicipality(String municipalityUuid) {

        MunicipalityBean municipalityBean = municipalityService.findByUuid(municipalityUuid);

        List<AreaBean> areas = areaRepository.findByMunicipalityBean(municipalityBean);

        return areas;
    }

    public String save(AreaBean areaBean, String municipalityUuid) {

        MunicipalityBean municipalityBean = municipalityService.findByUuid(municipalityUuid);

        areaBean.setMunicipalityBean(municipalityBean);

        PersonBean personBean = personService.saveMun(areaBean.getPersonBean());
        areaBean.setPersonBean(personBean);
        areaRepository.save(areaBean);

        return "Area registered successfully";
    }

    public String update(AreaBean areaBean, String municipalityUuid) {


       AreaBean areaUpdate =  getArea(areaBean.getUuid(), municipalityUuid);
       areaUpdate.setNameArea(areaBean.getNameArea());

        areaRepository.save(areaUpdate);
        return  "Updated successfully";
    }

    public String delete(AreaBean areaBean, String municipalityUuid) {
        AreaBean area =  getArea(areaBean.getUuid(), municipalityUuid);
        logger.info(area.getNameArea());

//        personService.delete(area.getPersonBean());
        return  "Delete successfully";
    }
}


