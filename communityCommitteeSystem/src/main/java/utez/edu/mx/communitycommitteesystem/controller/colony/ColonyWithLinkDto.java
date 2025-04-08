package utez.edu.mx.communitycommitteesystem.controller.colony;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.controller.person.PersonDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

@Getter
@Setter
public class ColonyWithLinkDto extends PersonDto {

    private String colonyName;
    private String uuid;
    public ColonyBean toEntity(){
        PersonBean personBean = getPersonBean();
        ColonyBean colonyBean = new ColonyBean();
        colonyBean.setPersonBean(personBean);
        colonyBean.setNameColony(colonyName);
        return colonyBean;
    }

}
