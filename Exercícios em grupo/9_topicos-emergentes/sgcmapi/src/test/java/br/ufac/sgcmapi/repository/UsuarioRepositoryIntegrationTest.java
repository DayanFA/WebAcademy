package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.ufac.sgcmapi.model.Usuario;

@DataJpaTest
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository repo;

    private PageRequest page;

    @BeforeEach
    public void setUp() {
        page = PageRequest.of(0, 5);
    }

    @Test
    public void testUsuarioSearchByNomeCompleto() {
        String termoBusca = "Administrador";
        Page<Usuario> usuarios = repo.busca(termoBusca, page);
        assertEquals(1, usuarios.getTotalElements());
        assertTrue(usuarios.getContent().get(0).getNomeCompleto().startsWith(termoBusca));
    }

    @Test
    public void testUsuarioSearchByNomeUsuario() {
        String termoBusca = "admin";
        Page<Usuario> usuarios = repo.busca(termoBusca, page);
        assertEquals(1, usuarios.getTotalElements());
        assertTrue(usuarios.getContent().get(0).getNomeUsuario().startsWith(termoBusca));
    }
    
}
