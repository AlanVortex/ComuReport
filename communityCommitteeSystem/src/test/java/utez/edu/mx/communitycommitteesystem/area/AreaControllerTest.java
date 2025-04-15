package utez.edu.mx.communitycommitteesystem.area;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import utez.edu.mx.communitycommitteesystem.controller.area.AreaDto;
import utez.edu.mx.communitycommitteesystem.controller.person.PersonUpdateContact;
import utez.edu.mx.communitycommitteesystem.exception.GlobalExceptionHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AreaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String areaUuid = "159cb393-6d2b-4cc0-b46a-863bcc765981"; // Usa un UUID real existente


    private String token;

    @BeforeEach
    void setUp() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "username": "tacp@gmail.com",
                          "password": "123456"
                        }
                    """))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        token = jsonNode.get("token").asText();
    }
    @Test
    void testGetAllByMunicipality() throws Exception {
        mockMvc.perform(get("/api/area")
                        .header("Authorization", "Bearer " + token)) // reemplaza <token> con uno válido si es necesario
                .andExpect(status().isOk());

    }

    @Test
    void testCreateArea() throws Exception {
        AreaDto dto = new AreaDto();
        dto.setName("John");
        dto.setLastname("Doe");
        dto.setEmail("area5@example.com");
        dto.setPhone("123456789");
        dto.setPassword("password");
        dto.setUuid("uuid");
        dto.setNameArea("Zona Centro Electric");
        // puedes añadir datos del administrador si hereda de PersonDto

        mockMvc.perform(post("/api/area")
                        .header("Authorization", "Bearer " + token) // usa un token válido si JWT está habilitado
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

    }

    @Test
    void testGetByMunicipality() throws Exception {

        mockMvc.perform(get("/api/area/{uuid}", areaUuid)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateArea() throws Exception {
        AreaDto dto = new AreaDto();
        dto.setName("John");
        dto.setPhone("325324234");
        dto.setPassword("password");
        dto.setEmail("area1@example.com");
        dto.setUuid(areaUuid); // Usa un UUID real existente
        dto.setNameArea("Zona Actualizada");
        PersonUpdateContact contact = new PersonUpdateContact();
        contact.setEmail("area2@example.com");
        contact.setPhone("123456789");
        mockMvc.perform(put("/api/area")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteArea() throws Exception {
        // Realiza la solicitud y obtiene la respuesta
        MvcResult result = mockMvc.perform(get("/api/area")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andReturn();

// Deserializa el JSON en una lista de AreaDto
        String jsonResponse = result.getResponse().getContentAsString();
        List<AreaDto> areas = objectMapper.readValue(jsonResponse, new TypeReference<List<AreaDto>>() {});

// Obtiene el uuid del primer elemento
        String uuid = areas.get(0).getUuid();
        System.out.println(uuid);
// Prepara y envía la solicitud DELETE
        AreaDto dto = new AreaDto();
        dto.setUuid(uuid);
        dto.setPassword("Zona Actualizada");

        mockMvc.perform(delete("/api/area")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

    }

}
