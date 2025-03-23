package utez.edu.mx.communitycommitteesystem.controller.committee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommitteeResponseDto {
    private String name;
    private String lastname;
    private String email;
    private String phone;

    public CommitteeResponseDto() {
    }

    public CommitteeResponseDto(String name, String lastname, String email, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
