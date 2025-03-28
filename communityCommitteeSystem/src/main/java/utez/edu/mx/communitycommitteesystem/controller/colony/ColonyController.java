package utez.edu.mx.communitycommitteesystem.controller.colony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colony")
@CrossOrigin(origins = "*")
public class ColonyController {

    @Autowired
    private MunicipalityService municipalityService;

    @Autowired
    private ColonyService colonyService;

    @PostMapping()
    public ResponseEntity<String> registerColonyWithLink(@RequestBody ColonyWithLinkDto dto) {
        try {
            colonyService.registerColonyWithLink(dto);
            return ResponseEntity.ok("Colonia y enlace colonial registrados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/municipality/{municipalityUuid}")
    public ResponseEntity<List<ColonyBean>> getColoniesByMunicipality(@PathVariable String municipalityUuid) {
        Optional<MunicipalityBean> municipalityOpt = municipalityService.findByUuid(municipalityUuid);
        if (!municipalityOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        MunicipalityBean municipality = municipalityOpt.get();
        List<ColonyBean> colonies = colonyService.findByMunicipality(municipality);

        return ResponseEntity.ok(colonies);
    }

    @GetMapping("/{colonyUuid}")
    public ResponseEntity<ColonyBean> getColonyByUuid(@PathVariable String colonyUuid) {
        Optional<ColonyBean> colonyOpt = colonyService.findByUuid(colonyUuid);
        if (!colonyOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(colonyOpt.get());
    }
}
