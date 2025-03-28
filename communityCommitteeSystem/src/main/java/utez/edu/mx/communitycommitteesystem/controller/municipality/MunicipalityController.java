package utez.edu.mx.communitycommitteesystem.controller.municipality;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/municipality")
@CrossOrigin(origins = "*")
public class MunicipalityController {

    @Autowired
    private MunicipalityService municipalityService;


    @PostMapping()
    public ResponseEntity<String> registerMunicipalityWithAdmin(@RequestBody AssignAdminMunicipalityDto dto) {
        try {
            String response = municipalityService.registerMunicipalityWithAdmin(dto);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor.");
        }
    }

    @GetMapping("/admins/{stateUuid}")
    public ResponseEntity<List<MunicipalityBean>> getMunicipalitiesByStateUuid(@PathVariable String stateUuid) {
        try {
            List<MunicipalityBean> municipalities = municipalityService.getMunicipalitiesByStateUuid(stateUuid);
            return ResponseEntity.ok(municipalities);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/admin/{municipalityUuid}")
    public ResponseEntity<PersonBean> getMunicipalityAdminByUuid(@PathVariable String municipalityUuid) {
        try {
            PersonBean admin = municipalityService.getMunicipalityAdminByUuid(municipalityUuid);
            return ResponseEntity.ok(admin);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
