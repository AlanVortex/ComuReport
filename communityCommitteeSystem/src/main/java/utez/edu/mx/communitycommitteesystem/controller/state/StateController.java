package utez.edu.mx.communitycommitteesystem.controller.state;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.exception.GlobalExceptionHandler;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/state")
public class StateController {




    private final StateService stateService;

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @PostMapping()
    public ResponseEntity<String> registerStateWithAdmin(@RequestBody StateWithAdminDto dto) {
            String result = stateService.registerStateWithAdmin(dto.toEntity());
            return ResponseEntity.ok(result);

    }
    @GetMapping("/{stateUuid}")
    public ResponseEntity<List<StateBean>> getStateAdminsByStateUuid(@PathVariable String stateUuid) {
        try {
            List<StateBean> stateAdmins = stateService.findStateAdminsByUuid(stateUuid);
            if (stateAdmins.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(stateAdmins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/admin/{adminUuid}")
    public ResponseEntity<StateBean> getStateAdminByUuid(@PathVariable String adminUuid) {
        try {
            StateBean admin = stateService.findStateAdminByUuid(adminUuid);
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
