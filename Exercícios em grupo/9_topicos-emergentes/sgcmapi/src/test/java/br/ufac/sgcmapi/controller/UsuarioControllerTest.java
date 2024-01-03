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
import br.ufac.sgcmapi.model.EPapel;
import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.service.UsuarioService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @MockBean
    private UsuarioService servico;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PerfilUsuarioService perfilUsuarioService;

    @Autowired
    private MockMvc mockMvc;

    private Usuario usuario;
    private Page<Usuario> usuarios;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setPapel(EPapel.ROLE_ADMIN);

        jsonContent = new ObjectMapper().writeValueAsString(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setPapel(EPapel.ROLE_ADMIN);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setPapel(EPapel.ROLE_ATENDENTE);

        List<Usuario> usuariosList = new ArrayList<>();
        usuariosList.add(usuario1);
        usuariosList.add(usuario2);
        usuarios = new PageImpl<>(usuariosList);

    }

    @Test
    public void testUsuarioGetAll() throws Exception {

        Mockito.when(servico.get(Mockito.any(Pageable.class))).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

    }

    @Test
    public void testUsuarioGetById() throws Exception {

        Mockito.when(servico.get(1L)).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testUsuarioGetByTermoBusca() throws Exception {

        Mockito.when(servico.get(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)));

    }

    @Test
    public void testUsuarioInsert() throws Exception {

        Mockito.when(servico.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/usuario/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testUsuarioUpdate() throws Exception {

        Mockito.when(servico.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.put("/config/usuario/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    public void testUsuarioDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/usuario/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(servico, times(1)).delete(1L);

    }

}