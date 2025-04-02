package utez.edu.mx.communitycommitteesystem.controller.municipality;

import lombok.Data;
import utez.edu.mx.communitycommitteesystem.controller.person.PersonDto;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

@Data
public class AssignAdminMunicipalityDto extends PersonDto {

    private String municipalityName;
    private String uuid;

    public MunicipalityBean toEntity(){
        MunicipalityBean municipalityBean = new MunicipalityBean();
        PersonBean person = getPersonBean();
        municipalityBean.setPersonBean(person);
        municipalityBean.setNameMunicipality(municipalityName);
        return municipalityBean;

    }

    public MunicipalityBean toEntityUpdate(){
        MunicipalityBean municipalityBean = new MunicipalityBean();
        municipalityBean.setNameMunicipality(municipalityName);
        municipalityBean.setUuid(uuid);
        return municipalityBean;

    }
}


