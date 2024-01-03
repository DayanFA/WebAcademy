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
public class PacienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testPacienteGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Giulia Farias Bencatel")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome", Matchers.is("Wallace Macedo Inácio Soriano")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome", Matchers.is("Helen Dutra Vilar")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[3].nome", Matchers.is("Jean Schuenck Amorin")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].nome", Matchers.is("Lucilene Santos Lucas")));
    }

    @Test
    @Order(2)
    public void testPacienteGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Giulia Farias Bencatel")));
    }

    @Test
    @Order(3)
    public void testPacienteGetByTermoBusca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/busca/Giulia"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Giulia Farias Bencatel")));
    }

    @Test
    @Order(4)
    public void testPacienteInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/pacienteInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Cristiana Marques Passos")));

    }

    @Test
    @Order(5)
    public void testPacienteUpdate() throws Exception {

        File jsonFile = new ClassPathResource("json/pacienteUpdate.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.put("/paciente/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.grupoSanguineo", Matchers.is("AB_POSITIVO")));

    }

    @Test
    @Order(6)
    public void testPacienteDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/6"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/"))
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)));

    }
    
}
