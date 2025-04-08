package utez.edu.mx.communitycommitteesystem.colony;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utez.edu.mx.communitycommitteesystem.controller.colony.ColonyWithLinkDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ColonyWithLinkDtoTest {

    @Test
    void ColonyWithLinkDtoTest(){
        ColonyWithLinkDto dto = new ColonyWithLinkDto();
        dto.setColonyName("Temixco");
        dto.setUuid("123e4567-e89b-12d3-a456-426614174000");
        dto.setName("John");
        dto.setLastname("Doe");
        dto.setEmail("john@example.com");
        dto.setPhone("123456789");
        dto.setPassword("password");
        dto.setUuid("uuid");

        ColonyBean result =  dto.toEntity();

        assertNotNull(result);
        assertEquals("Temixco", result.getNameColony());
        assertEquals("uuid",dto.getUuid());
    }
}
