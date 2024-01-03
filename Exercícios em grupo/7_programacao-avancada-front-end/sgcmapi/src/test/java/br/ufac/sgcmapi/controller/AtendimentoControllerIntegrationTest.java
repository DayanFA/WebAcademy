package br.ufac.sgcmapi.controller;

import java.io.File;
import java.nio.file.Files;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class AtendimentoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testAtendimentoGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].hora", Matchers.is("14:00:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].hora", Matchers.is("14:00:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].hora", Matchers.is("14:30:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[3].hora", Matchers.is("15:00:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[4].hora", Matchers.is("15:00:00")));
    }

    @Test
    @Order(2)
    public void testAtendimentoGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.hora", Matchers.is("14:00:00")));
    }

    @Test
    @Order(3)
    public void testAtendimentoGetByTermoBusca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/busca/Cardiologia"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].hora", Matchers.is("14:00:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].hora", Matchers.is("15:00:00")));
    }

    @Test
    @Order(4)
    public void testAtendimentoInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/atendimentoInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/atendimento/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.hora", Matchers.is("16:00:00")));
        
    }

    @Test
    @Order(5)
    public void testAtendimentoUpdate() throws Exception {

        File jsonFile = new ClassPathResource("json/atendimentoUpdate.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());
    
        mockMvc.perform(MockMvcRequestBuilders.put("/atendimento/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.hora", Matchers.is("17:00:00")));
    
        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/6"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.hora", Matchers.is("17:00:00")));

    }
    

    @Test
    @Order(6)
    public void testAtendimentoDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/atendimento/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(7)
    public void testAtendimentoUpdateStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/atendimento/status/2"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("CONFIRMADO")));
    }

    @Test
    @Order(8)
    public void testAtendimentoGetHorarios() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/horarios/1/2023-12-01"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.is("15:00:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1]", Matchers.is("17:00:00")));
    }
    
}
