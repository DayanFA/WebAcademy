package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Usuario;

@DataJpaTest
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository repo;

    @Test
    public void testUsuarioSearchByNomeCompleto() {
        String termoBusca = "Administrador";
        List<Usuario> usuarios = repo.busca(termoBusca);
        assertEquals(1, usuarios.size());
        assertTrue(usuarios.get(0).getNomeCompleto().startsWith(termoBusca));
    }

    @Test
    public void testUsuarioSearchByNomeUsuario() {
        String termoBusca = "admin";
        List<Usuario> usuarios = repo.busca(termoBusca);
        assertEquals(1, usuarios.size());
        assertTrue(usuarios.get(0).getNomeUsuario().startsWith(termoBusca));
    }
    
}
