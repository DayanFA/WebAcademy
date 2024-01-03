package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.ufac.sgcmapi.model.Profissional;

@DataJpaTest
public class ProfissionalRepositoryIntegrationTest {

    @Autowired
    private ProfissionalRepository repo;

    private PageRequest page;

    @BeforeEach
    public void setUp() {
        page = PageRequest.of(0, 5);
    }

    @Test
    public void testProfissionalSearchByNome() {
        String termoBusca = "Maria";
        Page<Profissional> profissionais = repo.busca(termoBusca, page);
        assertEquals(1, profissionais.getTotalElements());
        assertTrue(profissionais.getContent().get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByEmail() {
        String termoBusca = "elielson.andrade@gmail.com";
        Page<Profissional> profissionais = repo.busca(termoBusca, page);
        assertEquals(1, profissionais.getTotalElements());
        assertTrue(profissionais.getContent().get(0).getEmail().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByRegistroConselho() {
        String termoBusca = "CRM/AC 123";
        Page<Profissional> profissionais = repo.busca(termoBusca, page);
        assertEquals(1, profissionais.getTotalElements());
        assertTrue(profissionais.getContent().get(0).getRegistroConselho().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByTelefone() {
        String termoBusca = "(68) 98395-5604";
        Page<Profissional> profissionais = repo.busca(termoBusca, page);
        assertEquals(1, profissionais.getTotalElements());
        assertTrue(profissionais.getContent().get(0).getTelefone().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByEspecialidade() {
        String termoBusca = "Cardiologia";
        Page<Profissional> profissionais = repo.busca(termoBusca, page);
        assertEquals(2, profissionais.getTotalElements());
        assertTrue(profissionais.getContent().get(0).getEspecialidade().getNome().startsWith(termoBusca));
    }

    @Test
    public void testProfissionalSearchByUnidade() {
        String termoBusca = "Bela Vista";
        Page<Profissional> profissionais = repo.busca(termoBusca, page);
        assertEquals(3, profissionais.getTotalElements());
        assertTrue(profissionais.getContent().get(0).getUnidade().getNome().startsWith(termoBusca));
    }
    
}
