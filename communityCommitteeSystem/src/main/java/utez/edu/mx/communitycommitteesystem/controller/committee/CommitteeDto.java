package utez.edu.mx.communitycommitteesystem.controller.committee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;

@NoArgsConstructor
@Getter
@Setter
public class CommitteeDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private Long idColony;
    private Long idStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getIdColony() {
        return idColony;
    }

    public void setIdColony(Long idColony) {
        this.idColony = idColony;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public CommitteeBean toEntity(Long personId) {
        CommitteeBean committee = new CommitteeBean();

        PersonBean person = new PersonBean();
        person.setId(personId);
        person.setName(this.name);
        person.setLastname(this.lastname);
        person.setEmail(this.email);
        person.setPassword(this.password);
        person.setPhone(this.phone);

        committee.setPersonBean(person);

        ColonyBean colony = new ColonyBean();
        colony.setId(this.idColony);
        committee.setColonyBean(colony);

        StatusBean status = new StatusBean();
        status.setId(this.idStatus);
        committee.setStatusBean(status);

        return committee;
    }
}
