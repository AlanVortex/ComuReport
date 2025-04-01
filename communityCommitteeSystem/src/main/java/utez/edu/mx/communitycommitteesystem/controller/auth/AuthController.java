package utez.edu.mx.communitycommitteesystem.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.config.ApiResponse;
import utez.edu.mx.communitycommitteesystem.controller.auth.dto.SignDto;
import utez.edu.mx.communitycommitteesystem.controller.auth.dto.TokenDto;
import utez.edu.mx.communitycommitteesystem.service.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignDto dto) {
        return service.signIn
                (dto.getUsername(), dto.getPassword());
    }

}
