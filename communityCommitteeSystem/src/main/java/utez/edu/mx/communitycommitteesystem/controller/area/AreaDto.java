package utez.edu.mx.communitycommitteesystem.controller.area;


import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.controller.person.PersonDto;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

@Getter
@Setter
public class AreaDto extends PersonDto {
    private String nameArea;
    private String uuid;

    public AreaBean toEntity() {
        PersonBean personBean = getPersonBean();
        AreaBean areaBean = new AreaBean();
        areaBean.setNameArea(nameArea);
        areaBean.setPersonBean(personBean);
        return areaBean;
    }
    public AreaBean toEntityUpdate() {
        AreaBean areaBean = new AreaBean();
        areaBean.setNameArea(nameArea);
        areaBean.setUuid(uuid);
        return areaBean;
    }

}
