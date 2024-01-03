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

import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.service.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @MockBean
    private UsuarioService servico;

    @Autowired
    private MockMvc mockMvc;

    private Usuario usuario;
    private List<Usuario> usuarios;
    private String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        usuario = new Usuario();
        usuario.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);

        usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

    }

    @Test
    public void testUsuarioGetAll() throws Exception {

        Mockito.when(servico.get()).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

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

        Mockito.when(servico.get("termo")).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/busca/termo"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)));

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