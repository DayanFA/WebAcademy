package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Unidade;

@DataJpaTest
public class UnidadeRepositoryIntegrationTest {

    @Autowired
    private UnidadeRepository repo;

    @Test
    public void testUnidadeSearchByNome() {
        String termoBusca = "Bosque";
        List<Unidade> unidades = repo.busca(termoBusca);
        assertEquals(1, unidades.size());
        assertTrue(unidades.get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testUnidadeSearchByEndereco() {
        String termoBusca = "Rua";
        List<Unidade> unidades = repo.busca(termoBusca);
        assertEquals(3, unidades.size());
        assertTrue(unidades.get(0).getEndereco().startsWith(termoBusca));
    }
    
}
