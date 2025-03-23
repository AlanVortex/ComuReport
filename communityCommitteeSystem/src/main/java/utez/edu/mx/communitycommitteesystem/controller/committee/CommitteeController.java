package utez.edu.mx.communitycommitteesystem.controller.committee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.config.ApiResponse;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.committee.CommitteeService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.status.StatusService;
@RestController
@RequestMapping("/api/committee")
@CrossOrigin(origins = "*")
public class CommitteeController {

    @Autowired
    private CommitteeService committeeService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ColonyService colonyService;

    @Autowired
    private StatusService statusService;

    @PostMapping("/register")
    public ResponseEntity<String> registerPresident(@RequestBody CommitteeDto committeeDto) {
        try {
            PersonBean person = new PersonBean(
                    null,
                    committeeDto.getName(),
                    committeeDto.getLastname(),
                    committeeDto.getEmail(),
                    committeeDto.getPassword(),
                    committeeDto.getPhone()
            );
            personService.save(person);

            ColonyBean colony = colonyService.findById(committeeDto.getIdColony());

            StatusBean status = statusService.findById(committeeDto.getIdStatus());

            CommitteeBean committee = new CommitteeBean();
            committee.setPersonBean(person);
            committee.setColonyBean(colony);
            committee.setStatusBean(status);

            committeeService.save(committee);

            return ResponseEntity.ok("Presidente registrado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el presidente: " + e.getMessage());
        }
    }
}