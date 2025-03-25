package utez.edu.mx.communitycommitteesystem.controller.committee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.config.ApiResponse;
import utez.edu.mx.communitycommitteesystem.controller.status.UpdateStatusDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteeBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.committee.CommitteeService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.status.StatusService;
import utez.edu.mx.communitycommitteesystem.service.statusCommittee.StatusCommitteeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private StatusCommitteeService statusService;

    @PostMapping("/register")
    public ResponseEntity<String> registerPresident(@RequestBody CommitteeDto committeeDto) {
        Optional<ColonyBean> colonyOptional = colonyService.findByUuid(committeeDto.getColonyUuid());
        if (!colonyOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Error: Colonia no encontrada.");
        }
        ColonyBean colony = colonyOptional.get();

        PersonBean person = new PersonBean();
        person.setName(committeeDto.getName());
        person.setLastname(committeeDto.getLastname());
        person.setEmail(committeeDto.getEmail());
        person.setPassword(committeeDto.getPassword());
        person.setPhone(committeeDto.getPhone());

        PersonBean savedPerson = personService.saveMun(person);

        StatusCommitteeBean status = statusService.findById(committeeDto.getIdStatus());
        if (status == null) {
            return ResponseEntity.badRequest().body("Error: Status no encontrado.");
        }

        CommitteeBean committee = new CommitteeBean();
        committee.setPersonBean(savedPerson);
        committee.setColonyBean(colony);
        committee.setStatusCommitteeBean(status);

        committeeService.save(committee);

        return ResponseEntity.ok("Presidente registrado exitosamente con UUID: " + committee.getUuid());
    }

    @GetMapping("/all/{colonyUuid}")
    public ResponseEntity<List<CommitteeResponseDto>> getAllPresidentsByColony(@PathVariable String colonyUuid) {
        try {
            Optional<ColonyBean> colonyOptional = colonyService.findByUuid(colonyUuid);
            if (!colonyOptional.isPresent()) {
                return ResponseEntity.badRequest().body(null);
            }
            ColonyBean colony = colonyOptional.get();

            List<CommitteeBean> committees = committeeService.findByColony(colony);

            List<CommitteeResponseDto> committeeResponseDtos = committees.stream()
                    .map(committee -> {
                        PersonBean person = committee.getPersonBean();
                        if (person != null) {
                            return new CommitteeResponseDto(
                                    person.getName(),
                                    person.getLastname(),
                                    person.getEmail(),
                                    person.getPhone()
                            );
                        }
                        return null;
                    })
                    .filter(dto -> dto != null)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(committeeResponseDtos);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CommitteeResponseDto> getPresidentByUuid(@PathVariable String uuid) {
        Optional<CommitteeBean> committeeOptional = committeeService.findByUuid(uuid);

        if (committeeOptional.isPresent()) {
            CommitteeBean committee = committeeOptional.get();
            PersonBean person = committee.getPersonBean();

            if (person != null) {
                CommitteeResponseDto responseDto = new CommitteeResponseDto(
                        person.getName(),
                        person.getLastname(),
                        person.getEmail(),
                        person.getPhone()
                );
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<String> updatePresidentStatus(@PathVariable Long id, @RequestBody UpdateStatusDto request) {
        Optional<CommitteeBean> committeeOptional = committeeService.findById(id);

        if (committeeOptional.isPresent()) {
            CommitteeBean committee = committeeOptional.get();
            StatusCommitteeBean newStatus = new StatusCommitteeBean();
            newStatus.setId(request.getIdStatus());
            committee.setStatusCommitteeBean(newStatus);

            committeeService.save(committee);
            return ResponseEntity.ok("El estado del presidente ha sido actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Presidente no encontrado.");
        }
    }
}