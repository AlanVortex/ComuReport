package utez.edu.mx.communitycommitteesystem.controller.state;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/state")
public class StateController {




    private final StateService stateService;


    @PostMapping()
    public ResponseEntity<String> registerStateWithAdmin(@Valid @RequestBody StateWithAdminDto dto) {
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
