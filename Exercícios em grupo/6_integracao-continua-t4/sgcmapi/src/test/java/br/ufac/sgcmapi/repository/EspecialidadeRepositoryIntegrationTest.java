package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Especialidade;

@DataJpaTest
public class EspecialidadeRepositoryIntegrationTest {

    @Autowired
    private EspecialidadeRepository repo;

    @Test
    public void testEspecialidadeSearchByNome() {
        String termoBusca = "Cardiologia";
        List<Especialidade> especialidades = repo.busca(termoBusca);
        assertEquals(1, especialidades.size());
        assertTrue(especialidades.get(0).getNome().startsWith(termoBusca));
    }
    
}
