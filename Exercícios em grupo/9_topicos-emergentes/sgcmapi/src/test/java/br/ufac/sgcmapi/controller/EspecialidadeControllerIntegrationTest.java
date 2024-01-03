package br.ufac.sgcmapi.controller;

import java.io.File;
import java.nio.file.Files;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class EspecialidadeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @WithMockUser(roles = "ADMIN")
    public void testEspecialidadeGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(7)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nome", Matchers.is("Cardiologia")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].nome", Matchers.is("Dermatologia")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].nome", Matchers.is("Geriatria")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[3].nome", Matchers.is("Infectologia")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[4].nome", Matchers.is("Pediatria")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[5].nome", Matchers.is("Psiquiatria")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[6].nome", Matchers.is("Urologia")));
    }

    @Test
    @Order(2)
    @WithMockUser(roles = "ADMIN")
    public void testEspecialidadeGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Cardiologia")));
    }

    @Test
    @Order(3)
    @WithMockUser(roles = "ADMIN")
    public void testEspecialidadeGetByTermoBusca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/busca/gia"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(4)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nome", Matchers.is("Cardiologia")));
    }

    @Test
    @Order(4)
    @WithMockUser(roles = "ADMIN")
    public void testEspecialidadeInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/especialidadeInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/config/especialidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Neurologia")));

    }

    @Test
    @Order(5)
    @WithMockUser(roles = "ADMIN")
    public void testEspecialidadeUpdate() throws Exception {

        File jsonFile = new ClassPathResource("json/especialidadeUpdate.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.put("/config/especialidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Ortopedia")));

    }

    @Test
    @Order(6)
    @WithMockUser(roles = "ADMIN")
    public void testEspecialidadeDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/especialidade/8"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(7)));

    }
    
}
