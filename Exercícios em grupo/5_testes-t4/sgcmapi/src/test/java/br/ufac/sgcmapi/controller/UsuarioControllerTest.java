package br.ufac.sgcmapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    private String jsonContent;

    @BeforeEach
    private void setUp() throws JsonProcessingException {
        usuario = new Usuario();
        usuario.setId(1L);

        jsonContent = new ObjectMapper().writeValueAsString(usuario);
    }

    @Test
    public void testUsuarioInsert() throws Exception {
        Mockito.when(servico.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/usuario/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}