package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
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
import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceTest {

    @Mock
    private AtendimentoRepository repo;

    @InjectMocks
    private AtendimentoService servico;

    private Atendimento atendimento;
    private List<Atendimento> atendimentos;
    private Profissional profissional;

    @BeforeEach
    public void setUp() {

        atendimento = new Atendimento();
        atendimento.setId(1L);

        profissional = new Profissional();
        profissional.setId(1L);
        
        Atendimento atendimento1 = new Atendimento();
        atendimento1.setId(1L);
        atendimento1.setProfissional(profissional);
        atendimento1.setHora(LocalTime.of(15, 0));

        Atendimento atendimento2 = new Atendimento();
        atendimento2.setId(2L);
        atendimento2.setProfissional(profissional);
        atendimento2.setHora(LocalTime.of(16, 0));

        atendimentos = new ArrayList<>();
        atendimentos.add(atendimento1);
        atendimentos.add(atendimento2);

    }

    @Test
    public void testAtendimentoGetAll() {
        Mockito.when(repo.findAll()).thenReturn(atendimentos);
        List<Atendimento> result = servico.get();
        assertEquals(2, result.size());
    }

    @Test
    public void testAtendimentoGetById() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(atendimento));
        Atendimento result = servico.get(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testAtendimentoGetByTermoBusca() {
        Mockito.when(repo.busca("termo")).thenReturn(atendimentos);
        List<Atendimento> result = servico.get("termo");
        assertEquals(2, result.size());
    }

    @Test
    public void testAtendimentoSave() {
        Mockito.when(repo.save(atendimento)).thenReturn(atendimento);
        Atendimento result = servico.save(atendimento);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testAtendimentoDelete() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(atendimento));
        Mockito.when(repo.save(atendimento)).thenReturn(atendimento);
        servico.delete(1L);
        assertEquals(EStatus.CANCELADO, atendimento.getStatus());
    }

    @Test
    public void testAtendimentoUpdateStatus() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(atendimento));
        Mockito.when(repo.save(atendimento)).thenReturn(atendimento);
        Atendimento result = servico.updateStatus(1L);
        assertNotNull(result);
        assertEquals(EStatus.CONFIRMADO, result.getStatus());
    }

    @Test
    public void testAtendimentoGetHorarios() {
        Mockito.when(repo.findByProfissionalAndDataAndStatusNot(
                Mockito.any(Profissional.class),
                Mockito.eq(LocalDate.now()),
                Mockito.eq(EStatus.CANCELADO)))
            .thenReturn(atendimentos);
        List<String> result = servico.getHorarios(1L, LocalDate.now());
        assertEquals(2, result.size());
        assertTrue(result.contains("15:00:00"));
        assertTrue(result.contains("16:00:00"));
    }

}