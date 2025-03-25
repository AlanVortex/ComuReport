package utez.edu.mx.communitycommitteesystem.controller.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

@RestController
@RequestMapping("/api/state")
@CrossOrigin(origins = "*")
public class StateController {

    @Autowired
    private PersonService personService;

    @Autowired
    private StateService stateService;

    @PostMapping("/register-stateWithAdmin")
    public ResponseEntity<String> registerStateWithAdmin(@RequestBody StateWithAdminDto dto) {
        PersonBean person = new PersonBean();
        person.setName(dto.getName());
        person.setLastname(dto.getLastname());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());
        person.setPhone(dto.getPhone());

        PersonBean savedPerson = personService.saveState(person);

        StateBean state = new StateBean();
        state.setNameState(dto.getStateName());
        state.setPersonBean(savedPerson);

        stateService.save(state);

        return ResponseEntity.ok("Estado y administrador registrados correctamente");
    }
}
