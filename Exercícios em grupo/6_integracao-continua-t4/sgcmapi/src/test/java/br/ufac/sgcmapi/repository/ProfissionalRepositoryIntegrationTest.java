package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Profissional;

@DataJpaTest
public class ProfissionalRepositoryIntegrationTest {

    @Autowired
    private ProfissionalRepository repo;

    @Test
    public void testProfissionalSearchByNome() {
        String termoBusca = "Maria";
        List<Profissional> profissionais = repo.busca(termoBusca);
        assertEquals(1, profissionais.size());
        assertTrue(profissionais.get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByEmail() {
        String termoBusca = "elielson.andrade@gmail.com";
        List<Profissional> profissionais = repo.busca(termoBusca);
        assertEquals(1, profissionais.size());
        assertTrue(profissionais.get(0).getEmail().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByRegistroConselho() {
        String termoBusca = "CRM/AC 123";
        List<Profissional> profissionais = repo.busca(termoBusca);
        assertEquals(1, profissionais.size());
        assertTrue(profissionais.get(0).getRegistroConselho().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByTelefone() {
        String termoBusca = "(68) 98395-5604";
        List<Profissional> profissionais = repo.busca(termoBusca);
        assertEquals(1, profissionais.size());
        assertTrue(profissionais.get(0).getTelefone().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByEspecialidade() {
        String termoBusca = "Cardiologia";
        List<Profissional> profissionais = repo.busca(termoBusca);
        assertEquals(2, profissionais.size());
        assertTrue(profissionais.get(0).getEspecialidade().getNome().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByUnidade() {
        String termoBusca = "Bela Vista";
        List<Profissional> profissionais = repo.busca(termoBusca);
        assertEquals(3, profissionais.size());
        assertTrue(profissionais.get(0).getUnidade().getNome().startsWith(termoBusca));
    }
    
}
