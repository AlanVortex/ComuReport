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
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.committee.CommitteeService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.status.StatusService;

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
            StatusBean newStatus = new StatusBean();
            newStatus.setId(request.getIdStatus());
            committee.setStatusBean(newStatus);

            committeeService.save(committee);
            return ResponseEntity.ok("El estado del presidente ha sido actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Presidente no encontrado.");
        }
    }
}