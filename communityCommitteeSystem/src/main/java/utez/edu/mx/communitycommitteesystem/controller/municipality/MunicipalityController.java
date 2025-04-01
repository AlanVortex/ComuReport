package utez.edu.mx.communitycommitteesystem.controller.municipality;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.security.jwt.JwtProvider;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/municipality")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MunicipalityController {

    @Autowired
    private MunicipalityService municipalityService;

    private final JwtProvider jwtProvider;


    @PostMapping()
    public ResponseEntity<String> registerMunicipalityWithAdmin(@RequestBody AssignAdminMunicipalityDto dto, HttpServletRequest req) {

        String uuid = jwtProvider.resolveClaimsJUuid(req);
        String response = municipalityService.registerMunicipalityWithAdmin(dto.toEntity(), uuid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<MunicipalityBean>> getMunicipalitiesByStateUuid(HttpServletRequest req) {
        String uuid = jwtProvider.resolveClaimsJUuid(req);
        List<MunicipalityBean> municipalities = municipalityService.getMunicipalitiesByStateUuid(uuid);
        return ResponseEntity.ok(municipalities);

    }

    @GetMapping("{municipalityUuid}")
    public ResponseEntity<MunicipalityBean> getMunicipalityAdminByUuid(@PathVariable String municipalityUuid , HttpServletRequest req) {

            String uuid = jwtProvider.resolveClaimsJUuid(req);
            MunicipalityBean municipalityBean = municipalityService.getMunicipalityAdminByUuid(municipalityUuid,uuid );
            return ResponseEntity.ok(municipalityBean);

    }
    @PutMapping()
    public ResponseEntity<MunicipalityBean> update ( HttpServletRequest req , @RequestBody AssignAdminMunicipalityDto dto){

        String uuid = jwtProvider.resolveClaimsJUuid(req);
       MunicipalityBean municipalityBean=  municipalityService.update( dto.toEntityUpdate() , uuid);
        return ResponseEntity.ok(municipalityBean);
    }
    @DeleteMapping()
    public ResponseEntity<String> delete(HttpServletRequest req ,  @RequestBody AssignAdminMunicipalityDto dto) {
        String uuid = jwtProvider.resolveClaimsJUuid(req);
        municipalityService.delete( dto.toEntityUpdate() , uuid );
        return ResponseEntity.ok("Deleted");
    }

}
