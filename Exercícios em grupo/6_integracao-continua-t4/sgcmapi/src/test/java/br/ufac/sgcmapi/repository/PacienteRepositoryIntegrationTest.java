package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Paciente;

@DataJpaTest
public class PacienteRepositoryIntegrationTest {

    @Autowired
    private PacienteRepository repo;

    @Test
    public void testPacienteSearchByNome() {
        String termoBusca = "Giulia";
        List<Paciente> pacientes = repo.busca(termoBusca);
        assertEquals(1, pacientes.size());
        assertTrue(pacientes.get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testPacienteSearchByEmail() {
        String termoBusca = "wallace.soriano@yahoo.com";
        List<Paciente> pacientes = repo.busca(termoBusca);
        assertEquals(1, pacientes.size());
        assertTrue(pacientes.get(0).getEmail().startsWith(termoBusca));
    }

    @Test
    public void testPacienteSearchByTelefone() {
        String termoBusca = "(68) 99752-4954";
        List<Paciente> pacientes = repo.busca(termoBusca);
        assertEquals(1, pacientes.size());
        assertTrue(pacientes.get(0).getTelefone().startsWith(termoBusca));
    }
    
    @Test
    public void testPacienteSearchByCep() {
        String termoBusca = "69980-970";
        List<Paciente> pacientes = repo.busca(termoBusca);
        assertEquals(1, pacientes.size());
        assertTrue(pacientes.get(0).getCep().startsWith(termoBusca));
    }

    @Test
    public void testPacienteSearchByEndereco() {
        String termoBusca = "Rua Cedro";
        List<Paciente> pacientes = repo.busca(termoBusca);
        assertEquals(1, pacientes.size());
        assertTrue(pacientes.get(0).getEndereco().startsWith(termoBusca));
    }
    
}
