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

import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.service.ProfissionalService;

@WebMvcTest(ProfissionalController.class)
public class ProfissionalControllerTest {

    @MockBean
    private ProfissionalService servico;

    @Autowired
    private MockMvc mockMvc;

    private Profissional profissional;
    private List<Profissional> profissionais;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        profissional = new Profissional();
        profissional.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(profissional);

        Profissional profissional1 = new Profissional();
        profissional1.setId(1L);

        Profissional profissional2 = new Profissional();
        profissional2.setId(2L);

        profissionais = new ArrayList<>();
        profissionais.add(profissional1);
        profissionais.add(profissional2);

    }

    @Test
    public void testProfissionalGetAll() throws Exception {

        Mockito.when(servico.get()).thenReturn(profissionais);

        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testProfissionalGetById() throws Exception {

        Mockito.when(servico.get(1L)).thenReturn(profissional);

        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testProfissionalGetByTermoBusca() throws Exception {

        Mockito.when(servico.get("termo")).thenReturn(profissionais);

        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testProfissionalInsert() throws Exception {

        Mockito.when(servico.save(any(Profissional.class))).thenReturn(profissional);

        mockMvc.perform(MockMvcRequestBuilders.post("/profissional/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testProfissionalUpdate() throws Exception {

        Mockito.when(servico.save(any(Profissional.class))).thenReturn(profissional);

        mockMvc.perform(MockMvcRequestBuilders.put("/profissional/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testProfissionalDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/profissional/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico, times(1)).delete(1L);

    }

}