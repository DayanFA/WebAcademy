package br.ufac.sgcmapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.service.UnidadeService;

@WebMvcTest(UnidadeController.class)
public class UnidadeControllerTest {

    @MockBean
    private UnidadeService servico;

    @Autowired
    private MockMvc mockMvc;

    private Unidade unidade;
    private String jsonContent;

    @BeforeEach
    private void setUp() throws JsonProcessingException {
        unidade = new Unidade();
        unidade.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(unidade);
    }

    @Test
    public void testUnidadeInsert() throws Exception {
        Mockito.when(servico.save(any(Unidade.class))).thenReturn(unidade);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/unidade/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}