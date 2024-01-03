package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufac.sgcmapi.model.Convenio;
//import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.ConvenioRepository;

@ExtendWith(MockitoExtension.class)
public class ConvenioServiceTest {

    @Mock
    private ConvenioRepository repo;

    @InjectMocks
    private ConvenioService servico;

    private Convenio convenio;
    private List<Convenio> convenios;

    @BeforeEach
    public void setUp() {
        
        convenio = new Convenio();
        convenio.setId(1L);
        
        Convenio convenio1 = new Convenio();
        convenio1.setId(1L);
        
        Convenio convenio2 = new Convenio();
        convenio2.setId(2L);

        convenios = new ArrayList<>();
        convenios.add(convenio1);
        convenios.add(convenio2);

    }

    @Test
    public void testAtendimentoGetAll() {
        Mockito.when(repo.findAll()).thenReturn(convenios);
        List<Convenio> result = servico.get();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testAtendimentoUpdateStatus() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(convenio));
        Mockito.when(repo.save(convenio)).thenReturn(convenio);
        Convenio result = servico.updateStatus(1L);
        assertNotNull(result);
        //assertEquals(EStatus.CONFIRMADO, result.getStatus());
    }
    
}
