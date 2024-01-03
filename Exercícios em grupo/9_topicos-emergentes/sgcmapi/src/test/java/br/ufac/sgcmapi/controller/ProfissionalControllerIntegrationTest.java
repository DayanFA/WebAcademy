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
public class ProfissionalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @WithMockUser
    public void testProfissionalGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(5)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nome", Matchers.is("Maria Adelia Serravalle Bezerra")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].nome", Matchers.is("Elielson Silveira Andrade")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].nome", Matchers.is("Davi Jesus Mendes")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[3].nome", Matchers.is("Carla da Paixão Valle")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[4].nome", Matchers.is("Neuza Biango Nobrega")));
    }

    @Test
    @Order(2)
    @WithMockUser
    public void testProfissionalGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Maria Adelia Serravalle Bezerra")));
    }

    @Test
    @Order(3)
    @WithMockUser
    public void testProfissionalGetByTermoBusca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/busca/alle"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nome", Matchers.is("Maria Adelia Serravalle Bezerra")));
    }

    @Test
    @Order(4)
    @WithMockUser
    public void testProfissionalInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/profissionalInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/profissional/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Marlene Portugal Ferreira")));

    }

    @Test
    @Order(5)
    @WithMockUser
    public void testProfissionalUpdate() throws Exception {

        File jsonFile = new ClassPathResource("json/profissionalUpdate.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.put("/profissional/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.especialidade.id", Matchers.is(2)));

    }

    @Test
    @Order(6)
    @WithMockUser
    public void testProfissionalDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/profissional/6"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(5)));

    }
    
}
