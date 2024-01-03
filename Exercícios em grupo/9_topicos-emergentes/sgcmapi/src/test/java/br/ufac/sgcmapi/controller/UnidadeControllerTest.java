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
import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.service.UnidadeService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UnidadeController.class)
public class UnidadeControllerTest {

    @MockBean
    private UnidadeService servico;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PerfilUsuarioService perfilUsuarioService;

    @Autowired
    private MockMvc mockMvc;

    private Unidade unidade;
    private Page<Unidade> unidades;
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

        List<Unidade> unidadesList = new ArrayList<>();
        unidadesList.add(unidade1);
        unidadesList.add(unidade2);
        unidades = new PageImpl<>(unidadesList);

    }

    @Test
    public void testUnidadeGetAll() throws Exception {

        Mockito.when(servico.get(Mockito.any(Pageable.class))).thenReturn(unidades);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

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

        Mockito.when(servico.get(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(unidades);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

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