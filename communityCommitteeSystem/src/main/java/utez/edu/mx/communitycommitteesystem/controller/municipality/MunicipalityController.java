package utez.edu.mx.communitycommitteesystem.controller.municipality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.Optional;

@RestController
@RequestMapping("/api/municipality")
@CrossOrigin(origins = "*")
public class MunicipalityController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MunicipalityService municipalityService;

    @Autowired
    private StateService stateService;

    @PostMapping("/register-municipalityAdmin")
    public ResponseEntity<String> registerMunicipalityWithAdmin(@RequestBody AssignAdminMunicipalityDto dto) {
        StateBean state = stateService.findByUuid(dto.getStateUuid());
        if (state == null) {
            return ResponseEntity.badRequest().body("No se encontró un estado con el UUID proporcionado.");
        }

        PersonBean person = new PersonBean();
        person.setName(dto.getName());
        person.setLastname(dto.getLastname());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());
        person.setPhone(dto.getPhone());

        PersonBean savedPerson = personService.saveMun(person);

        MunicipalityBean municipality = new MunicipalityBean();
        municipality.setNameMunicipality(dto.getMunicipalityName());
        municipality.setPersonBean(savedPerson);
        municipality.setStateBean(state);

        municipalityService.save(municipality);

        return ResponseEntity.ok("Municipio y administrador registrados correctamente. UUID: " );
    }
}
