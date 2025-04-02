package utez.edu.mx.communitycommitteesystem.controller.colony;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.controller.person.PersonDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

@Data
public class ColonyWithLinkDto extends PersonDto {

    private String colonyName;
    private String uuid;
    private ColonyBean colony;
    public ColonyBean toEntity(){
        PersonBean personBean = getPersonBean();
        ColonyBean colonyBean = new ColonyBean();
        colonyBean.setPersonBean(personBean);
        colonyBean.setNameColony(colonyName);
        return colonyBean;
    }

    public ColonyBean getColony() {
        return colony;
    }

    public void setColony(ColonyBean colony) {
        this.colony = colony;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getColonyName() {
        return colonyName;
    }

    public void setColonyName(String colonyName) {
        this.colonyName = colonyName;
    }
}
