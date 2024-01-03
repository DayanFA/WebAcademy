package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.repository.UnidadeRepository;

@ExtendWith(MockitoExtension.class)
public class UnidadeServiceTest {

    @Mock
    private UnidadeRepository repo;

    @InjectMocks
    private UnidadeService servico;

    private Unidade unidade;
    private List<Unidade> unidades;

    @BeforeEach
    public void setUp() {
        
        unidade = new Unidade();
        unidade.setId(1L);
        
        Unidade unidade1 = new Unidade();
        unidade1.setId(1L);
        
        Unidade unidade2 = new Unidade();
        unidade2.setId(2L);

        unidades = new ArrayList<>();
        unidades.add(unidade1);
        unidades.add(unidade2);

    }

    @Test
    public void testUnidadeGetAll() {
        Mockito.when(repo.findAll()).thenReturn(unidades);
        List<Unidade> result = servico.get();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testUnidadeDelete() {
        Long id = 1L;
        Mockito.doNothing().when(repo).deleteById(id);
        servico.delete(id);
        Mockito.verify(repo, Mockito.times(1)).deleteById(id);
    }
    
}