package utez.edu.mx.communitycommitteesystem.controller.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String phone;
}
