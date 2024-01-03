package br.ufac.sgcmapi.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
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
public class AtendimentoControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void testAtendimentoGetAll() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/atendimento/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].hora", Matchers.is("14:00:00")));
    }

   @Test
   public void testAtendimentoInsert() throws Exception {

        File jsonFile = new ClassPathResource("json/atendimentoInsert.json").getFile();
        String jsonContent = Files.readString(jsonFile.toPath());

        mock.perform(MockMvcRequestBuilders.post("/atendimento/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.hora", Matchers.is("16:00:00")));

   }
    
}
