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
        // 1. Buscar la colonia usando Optional
        Optional<ColonyBean> colonyOptional = colonyService.findByUuid(committeeDto.getColonyUuid());
        if (!colonyOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Error: Colonia no encontrada.");
        }
        ColonyBean colony = colonyOptional.get();  // Obtener el valor de Optional

        // 2. Crear la persona (presidente)
        PersonBean person = new PersonBean();
        person.setName(committeeDto.getName());
        person.setLastname(committeeDto.getLastname());
        person.setEmail(committeeDto.getEmail());
        person.setPassword(committeeDto.getPassword());
        person.setPhone(committeeDto.getPhone());

        PersonBean savedPerson = personService.saveMun(person);

        // 3. Buscar el status por ID
        StatusCommitteeBean status = statusService.findById(committeeDto.getIdStatus());
        if (status == null) {
            return ResponseEntity.badRequest().body("Error: Status no encontrado.");
        }

        // 4. Crear el CommitteeBean con UUID autogenerado
        CommitteeBean committee = new CommitteeBean();
        committee.setPersonBean(savedPerson);
        committee.setColonyBean(colony);
        committee.setStatusCommitteeBean(status);

        // 5. Guardar el comité
        committeeService.save(committee);

        return ResponseEntity.ok("Presidente registrado exitosamente con UUID: " + committee.getUuid());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommitteeResponseDto>> getAllPresidents() {
        try {
            List<CommitteeBean> committees = committeeService.findAll();

            List<CommitteeResponseDto> committeeResponseDtos = committees.stream().map(committee -> {
                        PersonBean person = committee.getPersonBean();
                        if (person != null) {
                            return new CommitteeResponseDto(
                                    person.getName(),
                                    person.getLastname(),
                                    person.getEmail(),
                                    person.getPhone()
                            );
                        }
                        return null; // Si no hay persona asociada, retornar null o manejarlo de otra manera
                    }).filter(dto -> dto != null) // Filtrar los null si hay algún presidente sin persona
                    .collect(Collectors.toList());

            return ResponseEntity.ok(committeeResponseDtos);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommitteeResponseDto> getPresidentById(@PathVariable Long id) {
        Optional<CommitteeBean> committee = committeeService.findById(id);

        if (committee.isPresent()) {
            PersonBean person = committee.get().getPersonBean();
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