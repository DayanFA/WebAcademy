package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.ufac.sgcmapi.model.Unidade;

@DataJpaTest
public class UnidadeRepositoryIntegrationTest {

    @Autowired
    private UnidadeRepository repo;

    private PageRequest page;

    @BeforeEach
    public void setUp() {
        page = PageRequest.of(0, 5);
    }

    @Test
    public void testUnidadeSearchByNome() {
        String termoBusca = "Bosque";
        Page<Unidade> unidades = repo.busca(termoBusca, page);
        assertEquals(1, unidades.getTotalElements());
        assertTrue(unidades.getContent().get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testUnidadeSearchByEndereco() {
        String termoBusca = "Rua";
        Page<Unidade> unidades = repo.busca(termoBusca, page);
        assertEquals(3, unidades.getTotalElements());
        assertTrue(unidades.getContent().get(0).getEndereco().startsWith(termoBusca));
    }
    
}
