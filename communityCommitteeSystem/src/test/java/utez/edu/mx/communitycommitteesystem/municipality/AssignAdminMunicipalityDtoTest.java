package utez.edu.mx.communitycommitteesystem.municipality;

import org.junit.jupiter.api.Test;
import utez.edu.mx.communitycommitteesystem.controller.municipality.AssignAdminMunicipalityDto;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;

import static org.junit.jupiter.api.Assertions.*;

class AssignAdminMunicipalityDtoTest {

    @Test
    void toEntity_ShouldMapCorrectly() {
        // Arrange


        AssignAdminMunicipalityDto dto = new AssignAdminMunicipalityDto();
        dto.setNameMunicipality("Tlalnepantla");
        dto.setName("John");
        dto.setLastname("Doe");
        dto.setEmail("john@example.com");
        dto.setPhone("123456789");
        dto.setPassword("password");
        // Act
        MunicipalityBean result = dto.toEntity();

        // Assert
        assertNotNull(result);
        assertEquals("Tlalnepantla", dto.getNameMunicipality());
    }

    @Test
    void toEntityUpdate_ShouldMapCorrectly() {
        // Arrange
        AssignAdminMunicipalityDto dto = new AssignAdminMunicipalityDto();
        dto.setNameMunicipality("Naucalpan");
        dto.setUuid("321e4567-e89b-12d3-a456-426614174111");

        // Act
        MunicipalityBean result = dto.toEntityUpdate();

        // Assert
        assertNotNull(result);
        assertEquals("Naucalpan", result.getNameMunicipality());
        assertEquals("321e4567-e89b-12d3-a456-426614174111", dto.getUuid());
        assertNull(result.getPersonBean()); // No se asigna en este método
    }
}
