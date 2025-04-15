package utez.edu.mx.communitycommitteesystem.colony;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utez.edu.mx.communitycommitteesystem.controller.colony.ColonyWithLinkDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ColonyServiceTest {

    @Autowired
    private ColonyService colonyService;

    @Autowired
    private MunicipalityService municipalityService;

    String municipalityUuid = "fa77a705-606a-4f2c-b702-379ce61f6c9a";
    String colonyUuid = "e56b865b-bfad-4716-a49f-5a3d13435ea2";
/*
    @Test
    void findByUuidMunicipalityBean() {
        MunicipalityBean municipalityBean = municipalityService.findByUuid(municipalityUuid);
        assertNotNull(municipalityBean, "El municipio no debe ser null");

        ColonyBean colonyBean = colonyService.findByUuid(municipalityBean, colonyUuid);
        assertNotNull(colonyBean, "La colonia encontrada no debe ser null");
        assertEquals(colonyUuid, colonyBean.getUuid(), "UUID de colonia debe coincidir");
    }

    @Test
    void findByUuid() {
        ColonyBean colonyBean = colonyService.findByUuid(colonyUuid);
        assertNotNull(colonyBean, "La colonia encontrada no debe ser null");
        assertEquals(colonyUuid, colonyBean.getUuid(), "UUID de colonia debe coincidir");
    }

    @Test
    void get() {
        ColonyBean colonyBean = colonyService.get(colonyUuid, municipalityUuid);
        assertNotNull(colonyBean, "La colonia encontrada no debe ser null");
        assertEquals(colonyUuid, colonyBean.getUuid(), "UUID de colonia debe coincidir");
    }

    @Test
    void create() {
            ColonyBean colonyBean = new ColonyBean();
            PersonBean personBean = new PersonBean();
            personBean.setPhone("123456789");
            personBean.setEmail("test9@gmail.com");
            personBean.setName("name");
            personBean.setLastname("lastname");
            personBean.setPassword("password");

            colonyBean.setPersonBean(personBean);
            colonyBean.setNameColony("Test Colony");

            String created = colonyService.registerColonyWithLink(colonyBean, municipalityUuid);
        ColonyWithLinkDto colonyWithLinkDto = new ColonyWithLinkDto();
        colonyWithLinkDto.setUuid(municipalityUuid);
            assertNotNull(created, "La colonia creada no debe ser null");
            assertEquals("Colony Success", created, "El nombre de la colonia debe coincidir");
            colonyService.delete(colonyBean.getUuid(), colonyWithLinkDto);
    }

    @Test
    void findAll() {
        List<ColonyBean> colonies = colonyService.findAll(municipalityUuid);
        assertNotNull(colonies, "La lista de colonias no debe ser null");
        assertFalse(colonies.isEmpty(), "La lista de colonias no debe estar vacía");
    }

*/
}
