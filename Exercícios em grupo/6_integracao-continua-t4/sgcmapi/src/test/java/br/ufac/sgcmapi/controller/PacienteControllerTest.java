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

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.service.PacienteService;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @MockBean
    private PacienteService servico;

    @Autowired
    private MockMvc mockMvc;

    private Paciente paciente;
    private List<Paciente> pacientes;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        paciente = new Paciente();
        paciente.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(paciente);

        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);

        Paciente paciente2 = new Paciente();
        paciente2.setId(2L);

        pacientes = new ArrayList<>();
        pacientes.add(paciente1);
        pacientes.add(paciente2);

    }

    @Test
    public void testPacienteGetAll() throws Exception {

        Mockito.when(servico.get()).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testPacienteGetById() throws Exception {

        Mockito.when(servico.get(1L)).thenReturn(paciente);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testPacienteGetByTermoBusca() throws Exception {

        Mockito.when(servico.get("termo")).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testPacienteInsert() throws Exception {

        Mockito.when(servico.save(any(Paciente.class))).thenReturn(paciente);

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testPacienteUpdate() throws Exception {

        Mockito.when(servico.save(any(Paciente.class))).thenReturn(paciente);

        mockMvc.perform(MockMvcRequestBuilders.put("/paciente/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testPacienteDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico, times(1)).delete(1L);

    }

}