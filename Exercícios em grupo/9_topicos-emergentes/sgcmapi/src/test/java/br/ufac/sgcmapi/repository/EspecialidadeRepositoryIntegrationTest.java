package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.ufac.sgcmapi.model.Especialidade;

@DataJpaTest
public class EspecialidadeRepositoryIntegrationTest {

    @Autowired
    private EspecialidadeRepository repo;

    private PageRequest page;

    @BeforeEach
    public void setUp() {
        page = PageRequest.of(0, 5);
    }

    @Test
    public void testEspecialidadeSearchByNome() {
        String termoBusca = "Cardiologia";
        Page<Especialidade> especialidades = repo.busca(termoBusca, page);
        assertEquals(1, especialidades.getTotalElements());
        assertTrue(especialidades.getContent().get(0).getNome().startsWith(termoBusca));
    }
    
}
