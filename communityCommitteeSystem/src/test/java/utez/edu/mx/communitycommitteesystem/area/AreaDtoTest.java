package utez.edu.mx.communitycommitteesystem.area;

import org.junit.jupiter.api.Test;
import utez.edu.mx.communitycommitteesystem.controller.area.AreaDto;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;

import static org.junit.jupiter.api.Assertions.*;

class AreaDtoTest {

    @Test
    void toEntity_ShouldMapCorrectly() {
        // Arrange
        AreaDto dto = new AreaDto();
        dto.setNameArea("Zona Centro");
        dto.setName("John");
        dto.setLastname("Doe");
        dto.setEmail("john@example.com");
        dto.setPhone("123456789");
        dto.setPassword("password");
        dto.setUuid("uuid");
        // Act
        AreaBean result = dto.toEntity();

        // Assert
        assertNotNull(result);
        assertEquals("Zona Centro", result.getNameArea());
        assertEquals("uuid",dto.getUuid());
    }

    @Test
    void toEntityUpdate_ShouldMapCorrectly() {
        // Arrange
        AreaDto dto = new AreaDto();
        dto.setNameArea("Zona Sur");
        dto.setUuid("123e4567-e89b-12d3-a456-426614174000");

        // Act
        AreaBean result = dto.toEntityUpdate();

        // Assert
        assertNotNull(result);
        assertEquals("Zona Sur", dto.getNameArea());
        assertEquals("123e4567-e89b-12d3-a456-426614174000", result.getUuid());
        assertNull(result.getPersonBean()); // No se asigna en este método
    }
}
