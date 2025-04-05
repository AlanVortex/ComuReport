package utez.edu.mx.communitycommitteesystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${cors.origin}")
    private String corsOrigin;

    @Test
    void testCorsConfiguration() throws Exception {
        String requestBody = """
        {
          "username": "jared@gmail.com",
          "password": "123456"
        }
        """;


        mockMvc.perform(options("/api/auth/signin") // Simula una petición OPTIONS
                        .header("Origin", corsOrigin)
                        .header("Access-Control-Request-Method", "POST")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)) // Agrega el cuerpo JSON
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", corsOrigin));
    }

}
