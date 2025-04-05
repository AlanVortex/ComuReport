package utez.edu.mx.communitycommitteesystem.controller.area;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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

    //Crear una area , con verificacion y asginacion de su administrador de municipio
    @PostMapping("")
    public ResponseEntity<String> createArea(@RequestBody AreaDto dto, HttpServletRequest req) {

        return ResponseEntity.ok(areaService.save(dto.toEntity(), jwtProvider.resolveClaimsJUuid(req)));
    }

    //Ver todas las areas , con verificacion de su administrador de municipio
    @GetMapping("")
    public ResponseEntity<List<AreaBean>> getAllByMunicipality(HttpServletRequest req) {

        return ResponseEntity.ok(areaService.findByMunicipality(jwtProvider.resolveClaimsJUuid(req)));
    }

    //Ver una area , con verificacion de su administrador de municipio
    @GetMapping("/{uuid}")
    public ResponseEntity<AreaBean> getByMunicipality(HttpServletRequest req, @PathVariable String uuid) {
        return ResponseEntity.ok(areaService.getArea(uuid, jwtProvider.resolveClaimsJUuid(req)));
    }

    //Editar una area , con verificacion de su administrador de municipio
    @PutMapping("")
    public ResponseEntity<String> updateByMunicipality(HttpServletRequest req, @RequestBody AreaDto areaDto) {
        return ResponseEntity.ok(areaService.update(areaDto.toEntityUpdate(), jwtProvider.resolveClaimsJUuid(req)));
    }
    @DeleteMapping("")
    public ResponseEntity<String> deleteByMunicipality(HttpServletRequest req, @RequestBody AreaDto dto) {
        return ResponseEntity.ok(areaService.delete(dto.toEntityUpdate(), jwtProvider.resolveClaimsJUuid(req)));
    }

}
