package br.ufac.sgcmapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
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

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.service.AtendimentoService;

@WebMvcTest(AtendimentoController.class)
public class AtendimentoControllerTest {

    @MockBean
    private AtendimentoService servico;

    @Autowired
    private MockMvc mockMvc;

    private Atendimento atendimento;
    private List<Atendimento> atendimentos;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        atendimento = new Atendimento();
        atendimento.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(atendimento);

        Atendimento atendimento1 = new Atendimento();
        atendimento1.setId(1L);

        Atendimento atendimento2 = new Atendimento();
        atendimento2.setId(2L);

        atendimentos = new ArrayList<>();
        atendimentos.add(atendimento1);
        atendimentos.add(atendimento2);

    }

    @Test
    public void testAtendimentoGetAll() throws Exception {

        Mockito.when(servico.get()).thenReturn(atendimentos);

        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testAtendimentoGetById() throws Exception {

        Mockito.when(servico.get(1L)).thenReturn(atendimento);

        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testAtendimentoGetByTermoBusca() throws Exception {

        Mockito.when(servico.get("termo")).thenReturn(atendimentos);

        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

    }

    @Test
    public void testAtendimentoInsert() throws Exception {

        Mockito.when(servico.save(any(Atendimento.class))).thenReturn(atendimento);

        mockMvc.perform(MockMvcRequestBuilders.post("/atendimento/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testAtendimentoUpdate() throws Exception {

        Mockito.when(servico.save(any(Atendimento.class))).thenReturn(atendimento);

        mockMvc.perform(MockMvcRequestBuilders.put("/atendimento/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testAtendimentoDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/atendimento/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico, times(1)).delete(1L);

    }

    @Test
    public void testAtendimentoUpdateStatus() throws Exception {

        Mockito.when(servico.updateStatus(1L)).thenReturn(atendimento);

        mockMvc.perform(MockMvcRequestBuilders.put("/atendimento/status/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testAtendimentoGetHorarios() throws Exception {

        List<String> horarios = new ArrayList<>();
        horarios.add("15:00:00");
        horarios.add("16:00:00");

        Mockito.when(servico.getHorarios(1L, LocalDate.now())).thenReturn(horarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/horarios/1/" + LocalDate.now()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.is("15:00:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1]", Matchers.is("16:00:00")));
            
    }

}