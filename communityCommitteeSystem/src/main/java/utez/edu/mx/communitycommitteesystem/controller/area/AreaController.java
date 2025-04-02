package utez.edu.mx.communitycommitteesystem.controller.area;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.security.jwt.JwtProvider;
import utez.edu.mx.communitycommitteesystem.service.area.AreaService;

import java.util.List;

@RestController
@RequestMapping("/api/area")
@AllArgsConstructor
public class AreaController {
    private final AreaService areaService;

    private final JwtProvider jwtProvider;

    private static final Logger logger = LogManager.getLogger(AreaController.class);

    //Crear una area , con verificacion y asginacion de su administrador de municipio
    @PostMapping("")
    public ResponseEntity<String> createArea(@RequestBody AreaDto dto, HttpServletRequest req) {
        String uuid = jwtProvider.resolveClaimsJUuid(req);
        areaService.save(dto.toEntity(), uuid);
        return ResponseEntity.ok("Area registered successfully");
    }

    //Ver todas las areas , con verificacion de su administrador de municipio
    @GetMapping("")
    public ResponseEntity<List<AreaBean>> getAllByMunicipality(HttpServletRequest req) {

        String uuid = jwtProvider.resolveClaimsJUuid(req);
        return ResponseEntity.ok(areaService.findByMunicipality(uuid));
    }

    //Ver una area , con verificacion de su administrador de municipio
    @GetMapping("/{uuid}")
    public ResponseEntity<AreaBean> getByMunicipality(HttpServletRequest req, @PathVariable String uuid) {
        String uuidMunicipality = jwtProvider.resolveClaimsJUuid(req);
        return ResponseEntity.ok(areaService.getArea(uuid, uuidMunicipality));
    }

    //Editar una area , con verificacion de su administrador de municipio
    @PutMapping("")
    public ResponseEntity<String> updateByMunicipality(HttpServletRequest req, @RequestBody AreaDto areaDto) {
        String uuidMunicipality = jwtProvider.resolveClaimsJUuid(req);
        return ResponseEntity.ok(areaService.update(areaDto.toEntityUpdate(), uuidMunicipality));
    }
    @DeleteMapping("")
    public ResponseEntity<String> deleteByMunicipality(HttpServletRequest req, @RequestBody AreaDto dto) {
        String uuidMunicipality = jwtProvider.resolveClaimsJUuid(req);
        return ResponseEntity.ok(areaService.delete(dto.toEntityUpdate(), uuidMunicipality));
    }

}
