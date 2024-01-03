package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Convenio;

@DataJpaTest
public class ConvenioRepositoryTest {
    
    @Autowired
    private ConvenioRepository repo;

    @Test
    public void testAtendimentoSearchByProfissionalName() {
        String termoBusca = "Unimed";
        List<Convenio> convenios = repo.busca(termoBusca);
        assertEquals(1, convenios.size());
        assertTrue(convenios.get(0).getNome().startsWith(termoBusca));
    }
    
}
