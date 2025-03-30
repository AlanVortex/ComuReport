package utez.edu.mx.communitycommitteesystem.controller.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String phone;

    public PersonBean getPersonBean(){
        PersonBean personBean = new PersonBean();
        personBean.setName(name);
        personBean.setLastname(lastname);
        personBean.setEmail(email);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPsw = bcrypt.encode(password);
        personBean.setPassword(encryptedPsw);
        personBean.setPhone(phone);
        return personBean;
    }
}
