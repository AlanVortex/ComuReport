package utez.edu.mx.communitycommitteesystem.controller.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/admins/{stateUuid}")
    public ResponseEntity<List<StateBean>> getStateAdminsByStateUuid(@PathVariable String stateUuid) {
        Optional<StateBean> stateOpt = Optional.ofNullable(stateService.findByUuid(stateUuid));

        if (!stateOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        StateBean state = stateOpt.get();

        List<StateBean> stateAdmins = stateService.findByNameState(state.getNameState());
        return ResponseEntity.ok(stateAdmins);
    }

    @GetMapping("/admin/{adminUuid}")
    public ResponseEntity<StateBean> getStateAdminByUuid(@PathVariable String adminUuid) {
        Optional<StateBean> adminOpt = Optional.ofNullable(stateService.findByUuid(adminUuid));

        if (!adminOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        StateBean admin = adminOpt.get();
        return ResponseEntity.ok(admin);
    }

}
