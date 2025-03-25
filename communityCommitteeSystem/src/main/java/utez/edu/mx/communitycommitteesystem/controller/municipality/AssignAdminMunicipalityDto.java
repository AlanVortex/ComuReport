package utez.edu.mx.communitycommitteesystem.controller.municipality;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignAdminMunicipalityDto {

    private String municipalityName;

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String stateUuid;

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }


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

    public String getStateUuid() {
        return stateUuid;
    }
}


