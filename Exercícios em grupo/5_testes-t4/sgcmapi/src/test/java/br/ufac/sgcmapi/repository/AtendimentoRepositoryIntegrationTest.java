package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Atendimento;

@DataJpaTest
public class AtendimentoRepositoryIntegrationTest {
    
    @Autowired
    private AtendimentoRepository repo;

    @Test
    public void testAtendimentoSearchByProfissionalName() {
        String termoBusca = "Maria";
        List<Atendimento> atendimentos = repo.busca(termoBusca);
        assertEquals(2, atendimentos.size());
        assertTrue(atendimentos.get(0).getProfissional().getNome().startsWith(termoBusca));
    }
    
}
