package utez.edu.mx.communitycommitteesystem.controller.committee;

import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.controller.status.UpdateStatusDto;
import utez.edu.mx.communitycommitteesystem.exception.GlobalExceptionHandler;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.committee.CommitteeService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/committee")
public class CommitteeController {

    private CommitteeService committeeService;

    public CommitteeController(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    private static final Logger logger = LogManager.getLogger(CommitteeController.class);

    @PostMapping()
    public ResponseEntity<String> registerPresident(@RequestBody CommitteeDto committeeDto) {
        try {
            logger.info("Registro de presidente: " + committeeDto.toString());
            String response = committeeService.registerPresident(committeeDto);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error en el registro del presidente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @GetMapping("/all/{colonyUuid}")
    public ResponseEntity<List<CommitteeResponseDto>> getAllPresidentsByColony(@PathVariable String colonyUuid) {
        try {
            List<CommitteeBean> committees = committeeService.getCommitteesByColonyUuid(colonyUuid);

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
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(committeeResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<CommitteeResponseDto> getPresidentByUuid(@PathVariable String uuid) {
        try {
            CommitteeResponseDto responseDto = committeeService.getPresidentByUuid(uuid);
            return ResponseEntity.ok(responseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<String> updatePresidentStatus(@PathVariable Long id, @RequestBody UpdateStatusDto request) {
        try {
            String response = committeeService.updatePresidentStatus(id, request.getIdStatus());
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado.");
        }
    }

}