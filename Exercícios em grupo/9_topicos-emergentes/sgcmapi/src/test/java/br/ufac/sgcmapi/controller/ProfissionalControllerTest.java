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
import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.service.ProfissionalService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProfissionalController.class)
public class ProfissionalControllerTest {

    @MockBean
    private ProfissionalService servico;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PerfilUsuarioService perfilUsuarioService;

    @Autowired
    private MockMvc mockMvc;

    private Profissional profissional;
    private Page<Profissional> profissionais;
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

        List<Profissional> profissionaisList = new ArrayList<>();
        profissionaisList.add(profissional1);
        profissionaisList.add(profissional2);
        profissionais = new PageImpl<>(profissionaisList);

    }

    @Test
    public void testProfissionalGetAll() throws Exception {

        Mockito.when(servico.get(Mockito.any(Pageable.class))).thenReturn(profissionais);

        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

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

        Mockito.when(servico.get(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(profissionais);

        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

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