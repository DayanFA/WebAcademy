package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.ufac.sgcmapi.model.Paciente;

@DataJpaTest
public class PacienteRepositoryIntegrationTest {

    @Autowired
    private PacienteRepository repo;

    private PageRequest page;

    @BeforeEach
    public void setUp() {
        page = PageRequest.of(0, 5);
    }

    @Test
    public void testPacienteSearchByNome() {
        String termoBusca = "Giulia";
        Page<Paciente> pacientes = repo.busca(termoBusca, page);
        assertEquals(1, pacientes.getTotalElements());
        assertTrue(pacientes.getContent().get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testPacienteSearchByEmail() {
        String termoBusca = "wallace.soriano@yahoo.com";
        Page<Paciente> pacientes = repo.busca(termoBusca, page);
        assertEquals(1, pacientes.getTotalElements());
        assertTrue(pacientes.getContent().get(0).getEmail().startsWith(termoBusca));
    }

    @Test
    public void testPacienteSearchByTelefone() {
        String termoBusca = "(68) 99752-4954";
        Page<Paciente> pacientes = repo.busca(termoBusca, page);
        assertEquals(1, pacientes.getTotalElements());
        assertTrue(pacientes.getContent().get(0).getTelefone().startsWith(termoBusca));
    }
    
    @Test
    public void testPacienteSearchByCep() {
        String termoBusca = "69980-970";
        Page<Paciente> pacientes = repo.busca(termoBusca, page);
        assertEquals(1, pacientes.getTotalElements());
        assertTrue(pacientes.getContent().get(0).getCep().startsWith(termoBusca));
    }

    @Test
    public void testPacienteSearchByEndereco() {
        String termoBusca = "Rua Cedro";
        Page<Paciente> pacientes = repo.busca(termoBusca, page);
        assertEquals(1, pacientes.getTotalElements());
        assertTrue(pacientes.getContent().get(0).getEndereco().startsWith(termoBusca));
    }
    
}
