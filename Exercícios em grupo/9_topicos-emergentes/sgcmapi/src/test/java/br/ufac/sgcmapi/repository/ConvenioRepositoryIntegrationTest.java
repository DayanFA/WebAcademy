package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.ufac.sgcmapi.model.Convenio;

@DataJpaTest
public class ConvenioRepositoryIntegrationTest {

    @Autowired
    private ConvenioRepository repo;

    private PageRequest page;

    @BeforeEach
    public void setUp() {
        page = PageRequest.of(0, 5);
    }

    @Test
    public void testConvenioSearchByNome() {
        String termoBusca = "Unimed";
        Page<Convenio> convenios = repo.busca(termoBusca, page);
        assertEquals(1, convenios.getTotalElements());
        assertTrue(convenios.getContent().get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByRazaoSocial() {
        String termoBusca = "Unimed";
        Page<Convenio> convenios = repo.busca(termoBusca, page);
        assertEquals(1, convenios.getTotalElements());
        assertTrue(convenios.getContent().get(0).getRazaoSocial().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByCnpj() {
        String termoBusca = "46.560.030/0001-53";
        Page<Convenio> convenios = repo.busca(termoBusca, page);
        assertEquals(1, convenios.getTotalElements());
        assertTrue(convenios.getContent().get(0).getCnpj().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByRepresentante() {
        String termoBusca = "Davi";
        Page<Convenio> convenios = repo.busca(termoBusca, page);
        assertEquals(1, convenios.getTotalElements());
        assertTrue(convenios.getContent().get(0).getRepresentante().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByEmail() {
        String termoBusca = "contato@bradescosaude.com.br";
        Page<Convenio> convenios = repo.busca(termoBusca, page);
        assertEquals(1, convenios.getTotalElements());
        assertTrue(convenios.getContent().get(0).getEmail().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByTelefone() {
        String termoBusca = "(68) 3668-1546";
        Page<Convenio> convenios = repo.busca(termoBusca, page);
        assertEquals(1, convenios.getTotalElements());
        assertTrue(convenios.getContent().get(0).getTelefone().startsWith(termoBusca));
    }
    
}
