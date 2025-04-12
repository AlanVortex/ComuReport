package utez.edu.mx.communitycommitteesystem.controller.colony;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.security.jwt.JwtProvider;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/colony")
public class ColonyController {


    private final ColonyService colonyService;
    private final JwtProvider jwtProvider;

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody ColonyWithLinkDto dto, HttpServletRequest req) {

        return ResponseEntity.ok(colonyService.registerColonyWithLink(dto.toEntity() , jwtProvider.resolveClaimsJUuid(req)));
    }

    @GetMapping()
    public ResponseEntity<List<ColonyBean>> getAll(HttpServletRequest req) {
        return ResponseEntity.ok(colonyService.findAll(jwtProvider.resolveClaimsJUuid(req)));
    }


    @GetMapping("/{colonyUuid}")
    public ResponseEntity<ColonyBean> getColonyByUuid(@PathVariable String colonyUuid,HttpServletRequest req) {

        ColonyBean colonyBean = colonyService.get(colonyUuid ,jwtProvider.resolveClaimsJUuid(req));


        return ResponseEntity.ok(colonyBean);
    }
    @DeleteMapping()
    public ResponseEntity<String> delete(HttpServletRequest req ,  @RequestBody ColonyWithLinkDto dto) {
        colonyService.delete(jwtProvider.resolveClaimsJUuid(req) , dto.getUuid());

        return ResponseEntity.ok("Delete success");
    }
}
