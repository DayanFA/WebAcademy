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

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.service.UnidadeService;

@WebMvcTest(UnidadeController.class)
public class UnidadeControllerTest {

    @MockBean
    private UnidadeService servico;

    @Autowired
    private MockMvc mockMvc;

    private Unidade unidade;
    private List<Unidade> unidades;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        unidade = new Unidade();
        unidade.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(unidade);

        Unidade unidade1 = new Unidade();
        unidade1.setId(1L);

        Unidade unidade2 = new Unidade();
        unidade2.setId(2L);

        unidades = new ArrayList<>();
        unidades.add(unidade1);
        unidades.add(unidade2);

    }

    @Test
    public void testUnidadeGetAll() throws Exception {

        Mockito.when(servico.get()).thenReturn(unidades);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testUnidadeGetById() throws Exception {

        Mockito.when(servico.get(1L)).thenReturn(unidade);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testUnidadeGetByTermoBusca() throws Exception {

        Mockito.when(servico.get("termo")).thenReturn(unidades);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testUnidadeInsert() throws Exception {

        Mockito.when(servico.save(any(Unidade.class))).thenReturn(unidade);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/unidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testUnidadeUpdate() throws Exception {

        Mockito.when(servico.save(any(Unidade.class))).thenReturn(unidade);

        mockMvc.perform(MockMvcRequestBuilders.put("/config/unidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testUnidadeDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/unidade/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico, times(1)).delete(1L);

    }

}