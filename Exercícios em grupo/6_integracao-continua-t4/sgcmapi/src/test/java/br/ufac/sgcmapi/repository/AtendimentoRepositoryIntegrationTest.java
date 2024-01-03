package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;

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

    @Test
    public void testAtendimentoSearchByPatientName() {
        String termoBusca = "Giulia";
        List<Atendimento> atendimentos = repo.busca(termoBusca);
        assertEquals(1, atendimentos.size());
        assertTrue(atendimentos.get(0).getPaciente().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoSearchByConvenioName() {
        String termoBusca = "Unimed";
        List<Atendimento> atendimentos = repo.busca(termoBusca);
        assertEquals(3, atendimentos.size());
        assertTrue(atendimentos.get(0).getConvenio().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoSearchByUnidadeName() {
        String termoBusca = "Bosque";
        List<Atendimento> atendimentos = repo.busca(termoBusca);
        assertEquals(1, atendimentos.size());
        assertTrue(atendimentos.get(0).getProfissional().getUnidade().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoSearchByEspecialidadeName() {
        String termoBusca = "Cardiologia";
        List<Atendimento> atendimentos = repo.busca(termoBusca);
        assertEquals(2, atendimentos.size());
        assertTrue(atendimentos.get(0).getProfissional().getEspecialidade().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoFindByProfissionalAndData() {
        Atendimento atendimento = repo.findById(1L).get();
        List<Atendimento> atendimentos = repo.findByProfissionalAndDataAndStatusNot(
            atendimento.getProfissional(),
            atendimento.getData(),
            EStatus.CANCELADO);
        assertEquals(2, atendimentos.size());
    }
    
}
