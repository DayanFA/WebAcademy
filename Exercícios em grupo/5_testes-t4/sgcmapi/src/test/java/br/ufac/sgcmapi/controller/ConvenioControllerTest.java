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

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.service.ConvenioService;

@WebMvcTest(ConvenioController.class)
public class ConvenioControllerTest {

    @MockBean
    private ConvenioService servico;

    @Autowired
    private MockMvc mock;

    private Convenio Convenio;
    private String jsonContent;

    @BeforeEach
    private void setUp() throws JsonProcessingException {

        convenio = new Convenio();
        convenio.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(convenio);

    }

    @Test
    public void testConvenioInsert() throws Exception {

       Mockito.when(servico.save(any(Convenio.class))).thenReturn(convenio);

       mock.perform(MockMvcRequestBuilders.post("/convenio/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }
    
}
