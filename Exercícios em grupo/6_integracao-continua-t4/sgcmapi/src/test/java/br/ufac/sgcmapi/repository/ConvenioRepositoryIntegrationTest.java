package br.ufac.sgcmapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.ufac.sgcmapi.model.Convenio;

@DataJpaTest
public class ConvenioRepositoryIntegrationTest {

    @Autowired
    private ConvenioRepository repo;

    @Test
    public void testConvenioSearchByNome() {
        String termoBusca = "Unimed";
        List<Convenio> convenios = repo.busca(termoBusca);
        assertEquals(1, convenios.size());
        assertTrue(convenios.get(0).getNome().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByRazaoSocial() {
        String termoBusca = "Unimed";
        List<Convenio> convenios = repo.busca(termoBusca);
        assertEquals(1, convenios.size());
        assertTrue(convenios.get(0).getRazaoSocial().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByCnpj() {
        String termoBusca = "46.560.030/0001-53";
        List<Convenio> convenios = repo.busca(termoBusca);
        assertEquals(1, convenios.size());
        assertTrue(convenios.get(0).getCnpj().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByRepresentante() {
        String termoBusca = "Davi";
        List<Convenio> convenios = repo.busca(termoBusca);
        assertEquals(1, convenios.size());
        assertTrue(convenios.get(0).getRepresentante().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByEmail() {
        String termoBusca = "contato@bradescosaude.com.br";
        List<Convenio> convenios = repo.busca(termoBusca);
        assertEquals(1, convenios.size());
        assertTrue(convenios.get(0).getEmail().startsWith(termoBusca));
    }

    @Test
    public void testConvenioSearchByTelefone() {
        String termoBusca = "(68) 3668-1546";
        List<Convenio> convenios = repo.busca(termoBusca);
        assertEquals(1, convenios.size());
        assertTrue(convenios.get(0).getTelefone().startsWith(termoBusca));
    }
    
}
