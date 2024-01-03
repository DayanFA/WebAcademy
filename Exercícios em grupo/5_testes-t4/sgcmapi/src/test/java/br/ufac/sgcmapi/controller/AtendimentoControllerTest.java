package br.ufac.sgcmapi.controller;

import static org.mockito.ArgumentMatchers.any;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.service.AtendimentoService;

@WebMvcTest(AtendimentoController.class)
public class AtendimentoControllerTest {

    @MockBean
    private AtendimentoService servico;

    @Autowired
    private MockMvc mock;

    private Atendimento atendimento;
    private String jsonContent;

    @BeforeEach
    private void setUp() throws JsonProcessingException {

        atendimento = new Atendimento();
        atendimento.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(atendimento);

    }

    @Test
    public void testAtendimentoInsert() throws Exception {

       Mockito.when(servico.save(any(Atendimento.class))).thenReturn(atendimento);

       mock.perform(MockMvcRequestBuilders.post("/atendimento/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }
    
}
