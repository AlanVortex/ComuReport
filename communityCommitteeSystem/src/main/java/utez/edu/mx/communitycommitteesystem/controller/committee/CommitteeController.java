package utez.edu.mx.communitycommitteesystem.controller.committee;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.security.jwt.JwtProvider;
import utez.edu.mx.communitycommitteesystem.service.committee.CommitteeService;

import java.util.List;

@RestController
@RequestMapping("/api/committee")
@AllArgsConstructor
public class CommitteeController {

    private final CommitteeService committeeService;
    private final JwtProvider jwtProvider;


    @PostMapping()
    public ResponseEntity<String> registerPresident(@RequestBody CommitteeDto committeeDto, HttpServletRequest req) {
        return ResponseEntity.ok(committeeService.create(committeeDto.toEntity(), jwtProvider.resolveClaimsJUuid(req)));
    }
    @GetMapping()
    public ResponseEntity<List<CommitteeBean>> getAllCommittee(HttpServletRequest req) {
        return ResponseEntity.ok(committeeService.findAll(jwtProvider.resolveClaimsJUuid(req)));
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<CommitteeBean> getAllCommittee(HttpServletRequest req ,  @PathVariable String uuid) {
        return ResponseEntity.ok(committeeService.get(jwtProvider.resolveClaimsJUuid(req) , uuid));
    }


}