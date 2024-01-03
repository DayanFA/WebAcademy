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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class UnidadeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testUnidadeGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Bela Vista")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome", Matchers.is("Bosque")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome", Matchers.is("Cruzeiro do Sul")));
    }

    @Test
    @Order(2)
    public void testUnidadeGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Bela Vista")));
    }

    @Test
    @Order(3)
    public void testUnidadeGetByTermoBusca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/busca/Rua"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Bela Vista")));
    }

    @Test
    @Order(4)
    public void testUnidadeInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/unidadeInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/config/unidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Triângulo")));

    }

    @Test
    @Order(5)
    public void testUnidadeUpdate() throws Exception {

        File jsonFile = new ClassPathResource("json/unidadeUpdate.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.put("/config/unidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.endereco", Matchers.is("Via Chico Mendes, 1780 - Triângulo")));

    }

    @Test
    @Order(6)
    public void testUnidadeDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/unidade/4"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/"))
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

    }
    
}
