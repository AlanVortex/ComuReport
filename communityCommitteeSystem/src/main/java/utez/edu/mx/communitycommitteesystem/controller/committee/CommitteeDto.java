package utez.edu.mx.communitycommitteesystem.controller.committee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteeBean;

@NoArgsConstructor
@Getter
@Setter
public class CommitteeDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String colonyUuid;
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

    public String getColonyUuid() {
        return colonyUuid;
    }

    public void setColonyUuid(String colonyUuid) {
        this.colonyUuid = colonyUuid;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public CommitteeBean toEntity(Long personId) {
        CommitteeBean committee = new CommitteeBean();

        // Crear la persona
        PersonBean person = new PersonBean();
        person.setId(personId);
        person.setName(this.name);
        person.setLastname(this.lastname);
        person.setEmail(this.email);
        person.setPassword(this.password);
        person.setPhone(this.phone);

        committee.setPersonBean(person);

        // Buscar la colonia usando el UUID
        ColonyBean colony = new ColonyBean();
        colony.setUuid(this.colonyUuid);
        committee.setColonyBean(colony);

        // Crear el objeto StatusCommitteeBean y asignar el status
        StatusCommitteeBean status = new StatusCommitteeBean();
        status.setId(this.idStatus);
        committee.setStatusCommitteeBean(status);

        return committee;
    }
}
