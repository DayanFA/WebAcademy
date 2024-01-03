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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufac.sgcmapi.config.PerfilUsuarioService;
import br.ufac.sgcmapi.config.TokenService;
import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.service.PacienteService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @MockBean
    private PacienteService servico;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PerfilUsuarioService perfilUsuarioService;

    @Autowired
    private MockMvc mockMvc;

    private Paciente paciente;
    private Page<Paciente> pacientes;
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

        List<Paciente> pacientesList = new ArrayList<>();
        pacientesList.add(paciente1);
        pacientesList.add(paciente2);
        pacientes = new PageImpl<>(pacientesList);

    }

    @Test
    public void testPacienteGetAll() throws Exception {

        Mockito.when(servico.get(Mockito.any(Pageable.class))).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

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

        Mockito.when(servico.get(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(pacientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

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