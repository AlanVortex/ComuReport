package utez.edu.mx.communitycommitteesystem.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
 class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private PersonBean testPerson;
    private Long personId;

    @BeforeEach
     void setUp() {
        try {
            // Intentamos obtener la persona, si no existe, la creamos.
            PersonBean person = personService.getPerson(personId);
            if (person != null) {
                personId = person.getId();
            }
        } catch (Exception e) {
            // Si no existe, creamos una persona de prueba.
            testPerson = new PersonBean();
            ColonyBean colony = new ColonyBean();
            colony.setNameColony("colony");
            MunicipalityBean municipality = new MunicipalityBean();
            municipality.setNameMunicipality("Municipality");
            StateBean state = new StateBean();
            state.setNameState("state");
            AreaBean area = new AreaBean();
            area.setNameArea("area");

            testPerson.getRole();
            testPerson.getRoleUuid();
            testPerson.setStateBean(state);
            testPerson.getRole();
            testPerson.getRoleUuid();
            testPerson.setColonyBean(colony);
            testPerson.getRole();
            testPerson.getRoleUuid();

            testPerson.setMunicipalityBean(municipality);
            testPerson.getRole();
            testPerson.getRoleUuid();

            testPerson.setAreaBean(area);
            testPerson.getRole();
            testPerson.getRoleUuid();


            testPerson.setName("John");
            testPerson.setLastname("Doe");
            testPerson.setEmail("john.doe" + System.currentTimeMillis() + "@example.com"); // Correo único
            testPerson.setPhone("123456789");
            testPerson.setPassword("password");
            testPerson.getSmsBeanList();


            // Guardamos la persona creada
            testPerson = personService.save(testPerson);
            personId = testPerson.getId();

        }
    }

    @Test
     void testGetPerson() {
        // Obtener la persona por ID
        PersonBean retrievedPerson = personService.getPerson(personId);

        // Asegurarnos de que la persona recuperada no sea nula
        assertNotNull(retrievedPerson);
        assertEquals(personId, retrievedPerson.getId());
        assertEquals("John", testPerson.getName());
        assertEquals("state", testPerson.getStateBean().getNameState());
        assertEquals("colony", testPerson.getColonyBean().getNameColony());
        assertEquals("Municipality", testPerson.getMunicipalityBean().getNameMunicipality());
        assertEquals("area", testPerson.getAreaBean().getNameArea());

        assertEquals(null, testPerson.getToken());


        assertEquals("Doe", retrievedPerson.getLastname());
        personService.delete(personId);

    }

    @Test
     void testUpdatePerson() {
        // Obtener la persona creada previamente
        PersonBean createdPerson = personService.getPerson(personId);
        createdPerson.setLastname("Updated");

        // Actualizamos la persona
        PersonBean updatedPerson = personService.update(createdPerson);

        // Asegurarnos de que los cambios se hayan guardado
        assertNotNull(updatedPerson);
        assertEquals("John", updatedPerson.getName());
        assertEquals("Updated", updatedPerson.getLastname());

        personService.delete(personId);

    }


}
