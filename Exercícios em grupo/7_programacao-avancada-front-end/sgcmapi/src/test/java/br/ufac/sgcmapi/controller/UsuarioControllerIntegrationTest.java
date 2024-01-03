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
public class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testUsuarioGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nomeCompleto", Matchers.is("Administrador")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].nomeCompleto", Matchers.is("Daniel")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].nomeCompleto", Matchers.is("Paulo")));
    }

    @Test
    @Order(2)
    public void testUsuarioGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nomeCompleto", Matchers.is("Administrador")));
    }

    @Test
    @Order(3)
    public void testUsuarioGetByTermoBusca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/busca/admin"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nomeCompleto", Matchers.is("Administrador")));
    }

    @Test
    @Order(4)
    public void testUsuarioInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/usuarioInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/config/usuario/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nomeCompleto", Matchers.is("Laura")));

    }

    @Test
    @Order(5)
    public void testUsuarioUpdate() throws Exception {

        File jsonFile = new ClassPathResource("json/usuarioUpdate.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mockMvc.perform(MockMvcRequestBuilders.put("/config/usuario/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nomeCompleto", Matchers.is("Laura Costa Sarkis")));

    }

    @Test
    @Order(6)
    public void testUsuarioDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/usuario/4"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/"))
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

    }
    
}
