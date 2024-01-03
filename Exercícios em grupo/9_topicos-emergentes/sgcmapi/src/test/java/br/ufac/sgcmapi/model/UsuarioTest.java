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
        assertEquals(id, usuario.getId());
    }

    @Test
    public void testUsuarioNomeCompleto() {
        String nome = "nome";
        usuario.setNomeCompleto(nome);
        assertEquals(nome, usuario.getNomeCompleto());
    }

    @Test
    public void testUsuarioNomeUsuario() {
        String nome = "nome";
        usuario.setNomeUsuario(nome);
        assertEquals(nome, usuario.getNomeUsuario());
    }

    @Test
    public void testUsuarioSenha() {
        String senha = "senha";
        usuario.setSenha(senha);
        assertEquals(senha, usuario.getSenha());
    }

    @Test
    public void testUsuarioPapel() {
        EPapel papel = EPapel.ROLE_ADMIN;
        usuario.setPapel(papel);
        assertEquals(papel, usuario.getPapel());
    }

    @Test
    public void testUsuarioAtivo() {
        Boolean ativo = true;
        usuario.setAtivo(ativo);
        assertEquals(ativo, usuario.isAtivo());
    }
    
}
