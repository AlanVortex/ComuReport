package utez.edu.mx.communitycommitteesystem.controller.colony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import java.util.Optional;

@RestController
@RequestMapping("/api/colony")
@CrossOrigin(origins = "*")
public class ColonyController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MunicipalityService municipalityService;

    @Autowired
    private ColonyService colonyService;

    @PostMapping("/register-colonyWithLink")
    public ResponseEntity<String> registerColonyWithLink(@RequestBody ColonyWithLinkDto dto) {
        PersonBean person = new PersonBean();
        person.setName(dto.getName());
        person.setLastname(dto.getLastname());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());
        person.setPhone(dto.getPhone());

        PersonBean savedPerson = personService.saveColony(person);

        Optional<MunicipalityBean> municipalityOpt = municipalityService.findByUuid(dto.getMunicipalityUuid());
        if (!municipalityOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Error: Municipio no encontrado.");
        }

        ColonyBean colony = new ColonyBean();
        colony.setNameColony(dto.getColonyName());
        colony.setMunicipalityBean(municipalityOpt.get());
        colony.setPersonBean(savedPerson);

        colonyService.save(colony);

        return ResponseEntity.ok("Colonia y enlace colonial registrados correctamente.");
    }
}
