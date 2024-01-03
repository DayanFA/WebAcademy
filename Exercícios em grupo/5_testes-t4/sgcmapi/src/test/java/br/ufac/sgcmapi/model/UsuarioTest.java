package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
    }

    @Test
    public void testUsuarioId() {
        Long id = 1L;
        usuario.setId(id);
        assertEquals(1L, usuario.getId());
    }
    
}