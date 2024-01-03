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

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.service.EspecialidadeService;

@WebMvcTest(EspecialidadeController.class)
public class EspecialidadeControllerTest {

    @MockBean
    private EspecialidadeService servico;

    @Autowired
    private MockMvc mockMvc;

    private Especialidade especialidade;
    private List<Especialidade> especialidades;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        especialidade = new Especialidade();
        especialidade.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(especialidade);

        Especialidade especialidade1 = new Especialidade();
        especialidade1.setId(1L);

        Especialidade especialidade2 = new Especialidade();
        especialidade2.setId(2L);

        especialidades = new ArrayList<>();
        especialidades.add(especialidade1);
        especialidades.add(especialidade2);

    }

    @Test
    public void testEspecialidadeGetAll() throws Exception {

        Mockito.when(servico.get()).thenReturn(especialidades);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testEspecialidadeGetById() throws Exception {

        Mockito.when(servico.get(1L)).thenReturn(especialidade);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testEspecialidadeGetByTermoBusca() throws Exception {

        Mockito.when(servico.get("termo")).thenReturn(especialidades);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testEspecialidadeInsert() throws Exception {

        Mockito.when(servico.save(any(Especialidade.class))).thenReturn(especialidade);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/especialidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testEspecialidadeUpdate() throws Exception {

        Mockito.when(servico.save(any(Especialidade.class))).thenReturn(especialidade);

        mockMvc.perform(MockMvcRequestBuilders.put("/config/especialidade/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testEspecialidadeDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/especialidade/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico, times(1)).delete(1L);

    }

}