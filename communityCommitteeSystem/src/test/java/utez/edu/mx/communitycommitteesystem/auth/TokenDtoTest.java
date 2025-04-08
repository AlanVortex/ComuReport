package utez.edu.mx.communitycommitteesystem.auth;

import org.junit.jupiter.api.Test;
import utez.edu.mx.communitycommitteesystem.controller.auth.dto.TokenDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TokenDtoTest {

    @Test
    void getTitle() {
        TokenDto dto = new TokenDto();
        dto.setToken("token");
        dto.setRole("role");
        dto.setMessage("message");

        assertNotNull(dto);
        assertEquals("token", dto.getToken());
        assertEquals("message", dto.getMessage());
        assertEquals("role", dto.getRole());
    }
}