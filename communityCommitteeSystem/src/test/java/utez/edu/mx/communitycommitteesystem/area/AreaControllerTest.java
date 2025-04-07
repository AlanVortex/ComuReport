package utez.edu.mx.communitycommitteesystem.area;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import utez.edu.mx.communitycommitteesystem.controller.area.AreaDto;

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


    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvY3VsY29AZ21haWwuY29tIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6Ik11bmljaXBhbGl0eSJ9XSwidXVpZCI6IjJjYjM4MDFhLTNlZjItNGFlMi1hYWQyLTc2N2Y0NzliYTlkYiIsImlhdCI6MTc0Mzk4ODIxNSwiZXhwIjoxNzQ0NTkzMDE1fQ.9g5bhOYV9cvF5TytHabFaT0RGbajI3cs5bGHwuXqEkk";

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
        dto.setUuid(areaUuid); // Usa un UUID real existente
        dto.setNameArea("Zona Actualizada");

        mockMvc.perform(put("/api/area")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto.toEntityUpdate())))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteArea() throws Exception {
        // Realiza la solicitud y obtiene la respuesta
        MvcResult result = mockMvc.perform(get("/api/area")
                        .header("Authorization", "Bearer " + token)) // reemplaza <token> con uno válido si es necesario
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())  // Verifica que la respuesta es un arreglo
                .andExpect(jsonPath("$[0]").exists())  // Verifica que el primer elemento existe
                .andReturn();  // Captura el resultado de la solicitud

// Extrae el valor del 'uuid' del primer elemento
            String uuid = JsonPath.read(result.getResponse().getContentAsString(), "$[0].uuid");
        AreaDto dto = new AreaDto();
        dto.setUuid(uuid);
        mockMvc.perform(delete("/api/area")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto.toEntityUpdate())))
                .andExpect(status().isOk());
    }

}
