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

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceTest {

    @Mock
    private AtendimentoRepository repo;

    @InjectMocks
    private AtendimentoService servico;

    private Atendimento atendimento;
    private List<Atendimento> atendimentos;

    @BeforeEach
    public void setUp() {
        
        atendimento = new Atendimento();
        atendimento.setId(1L);
        
        Atendimento atendimento1 = new Atendimento();
        atendimento1.setId(1L);
        
        Atendimento atendimento2 = new Atendimento();
        atendimento2.setId(2L);

        atendimentos = new ArrayList<>();
        atendimentos.add(atendimento1);
        atendimentos.add(atendimento2);

    }

    @Test
    public void testAtendimentoGetAll() {
        Mockito.when(repo.findAll()).thenReturn(atendimentos);
        List<Atendimento> result = servico.get();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testAtendimentoUpdateStatus() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(atendimento));
        Mockito.when(repo.save(atendimento)).thenReturn(atendimento);
        Atendimento result = servico.updateStatus(1L);
        assertNotNull(result);
        assertEquals(EStatus.CONFIRMADO, result.getStatus());
    }
    
}
