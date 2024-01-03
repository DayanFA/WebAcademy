package br.ufac.sgcmapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

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
    private MockMvc mockMvc;

    private Convenio convenio;
    private List<Convenio> convenios;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        convenio = new Convenio();
        convenio.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(convenio);

        Convenio convenio1 = new Convenio();
        convenio1.setId(1L);

        Convenio convenio2 = new Convenio();
        convenio2.setId(2L);

        convenios = new ArrayList<>();
        convenios.add(convenio1);
        convenios.add(convenio2);

    }

    @Test
    public void testConvenioGetAll() throws Exception {

        Mockito.when(servico.get()).thenReturn(convenios);

        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testConvenioGetById() throws Exception {

        Mockito.when(servico.get(1L)).thenReturn(convenio);

        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testConvenioGetByTermoBusca() throws Exception {

        Mockito.when(servico.get("termo")).thenReturn(convenios);

        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testConvenioInsert() throws Exception {

        Mockito.when(servico.save(any(Convenio.class))).thenReturn(convenio);

        mockMvc.perform(MockMvcRequestBuilders.post("/convenio/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testConvenioUpdate() throws Exception {

        Mockito.when(servico.save(any(Convenio.class))).thenReturn(convenio);

        mockMvc.perform(MockMvcRequestBuilders.put("/convenio/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testConvenioDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/convenio/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico, times(1)).delete(1L);

    }

}