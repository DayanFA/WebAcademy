package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;

@DataJpaTest
public class AtendimentoRepositoryIntegrationTest {

    @Autowired
    private AtendimentoRepository repo;

    private PageRequest page;

    @BeforeEach
    public void setUp() {
        page = PageRequest.of(0, 5);
    }

    @Test
    public void testAtendimentoSearchByProfissionalName() {
        String termoBusca = "Maria";
        Page<Atendimento> atendimentos = repo.busca(termoBusca, null, null, page);
        assertEquals(2, atendimentos.getTotalElements());
        assertTrue(atendimentos.getContent().get(0).getProfissional().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoSearchByPatientName() {
        String termoBusca = "Giulia";
        Page<Atendimento> atendimentos = repo.busca(termoBusca, null, null, page);
        assertEquals(1, atendimentos.getTotalElements());
        assertTrue(atendimentos.getContent().get(0).getPaciente().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoSearchByConvenioName() {
        String termoBusca = "Unimed";
        Page<Atendimento> atendimentos = repo.busca(termoBusca, null, null, page);
        assertEquals(3, atendimentos.getTotalElements());
        assertTrue(atendimentos.getContent().get(0).getConvenio().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoSearchByUnidadeName() {
        String termoBusca = "Bosque";
        Page<Atendimento> atendimentos = repo.busca(termoBusca, null, null, page);
        assertEquals(1, atendimentos.getTotalElements());
        assertTrue(atendimentos.getContent().get(0).getProfissional().getUnidade().getNome().startsWith(termoBusca));
    }

    @Test
    public void testAtendimentoSearchByEspecialidadeName() {
        String termoBusca = "Cardiologia";
        Page<Atendimento> atendimentos = repo.busca(termoBusca, null, null, page);
        assertEquals(2, atendimentos.getTotalElements());
        assertTrue(atendimentos.getContent().get(0).getProfissional().getEspecialidade().getNome().startsWith(termoBusca));
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
