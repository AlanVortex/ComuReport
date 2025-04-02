package utez.edu.mx.communitycommitteesystem.controller.colony;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.security.jwt.JwtProvider;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/colony")
public class ColonyController {


    private final ColonyService colonyService;
    private final JwtProvider jwtProvider;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody ColonyWithLinkDto dto,HttpServletRequest req) {
        String uuid = jwtProvider.resolveClaimsJUuid(req);
        return ResponseEntity.ok(colonyService.registerColonyWithLink(dto.toEntity() , uuid));
    }

    @GetMapping()
    public ResponseEntity<List<ColonyBean>> getAll(HttpServletRequest req) {
        String uuid = jwtProvider.resolveClaimsJUuid(req);
        return ResponseEntity.ok(colonyService.findAll(uuid));
    }


    @GetMapping("/{colonyUuid}")
    public ResponseEntity<ColonyBean> getColonyByUuid(@PathVariable String colonyUuid,HttpServletRequest req) {
        String uuid = jwtProvider.resolveClaimsJUuid(req);
        ColonyBean colonyBean = colonyService.get(colonyUuid , uuid);


        return ResponseEntity.ok(colonyBean);
    }
    @DeleteMapping()
    public ResponseEntity<String> delete(HttpServletRequest req ,  @RequestBody ColonyWithLinkDto dto) {
        String uuid = jwtProvider.resolveClaimsJUuid(req);
        colonyService.delete(uuid , dto.getUuid());

        return ResponseEntity.ok("Delete success");
    }
}
