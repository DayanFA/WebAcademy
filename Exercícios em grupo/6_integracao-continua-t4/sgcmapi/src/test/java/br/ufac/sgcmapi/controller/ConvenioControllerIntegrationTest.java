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
public class ConvenioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testConvenioGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Unimed")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome", Matchers.is("Amil")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].nome", Matchers.is("Bradesco")));
    }

    @Test
    @Order(2)
    public void testConvenioGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Unimed")));
    }

    @Test
    @Order(3)
    public void testConvenioGetByTermoBusca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/busca/Unimed"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Unimed")));
    }

    @Test
    @Order(4)
    public void testConvenioInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/convenioInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/convenio/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Real")));

    }

    @Test
    @Order(5)
    public void testConvenioUpdate() throws Exception {

        File jsonFile = new ClassPathResource("json/convenioUpdate.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.put("/convenio/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.ativo", Matchers.is(false)));

    }

    @Test
    @Order(6)
    public void testConvenioDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/convenio/4"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/"))
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

    }
    
}
