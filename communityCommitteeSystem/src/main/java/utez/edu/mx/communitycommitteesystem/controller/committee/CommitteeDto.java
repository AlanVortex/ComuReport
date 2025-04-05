package utez.edu.mx.communitycommitteesystem.controller.committee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.controller.person.PersonDto;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

@NoArgsConstructor
@Getter
@Setter
public class CommitteeDto extends PersonDto {

    private String nameCommittee;

    public CommitteeBean toEntity() {
        PersonBean personBean = getPersonBean();
        CommitteeBean committeeBean = new CommitteeBean();
        committeeBean.setNameCommittee(nameCommittee);
        committeeBean.setPersonBean(personBean);
        return committeeBean;
    }

}
