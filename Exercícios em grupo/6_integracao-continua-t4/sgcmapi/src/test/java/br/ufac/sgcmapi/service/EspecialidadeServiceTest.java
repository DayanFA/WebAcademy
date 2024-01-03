package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

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

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.repository.EspecialidadeRepository;

@ExtendWith(MockitoExtension.class)
public class EspecialidadeServiceTest {

    @Mock
    private EspecialidadeRepository repo;

    @InjectMocks
    private EspecialidadeService servico;

    private Especialidade especialidade;
    private List<Especialidade> especialidades;

    @BeforeEach
    public void setUp() {

        especialidade = new Especialidade();
        especialidade.setId(1L);
        
        Especialidade especialidade1 = new Especialidade();
        especialidade1.setId(1L);

        Especialidade especialidade2 = new Especialidade();
        especialidade2.setId(2L);

        especialidades = new ArrayList<>();
        especialidades.add(especialidade1);
        especialidades.add(especialidade2);

    }

    @Test
    public void testEspecialidadeGetAll() {
        Mockito.when(repo.findAll()).thenReturn(especialidades);
        List<Especialidade> result = servico.get();
        assertEquals(2, result.size());
    }

    @Test
    public void testEspecialidadeGetById() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(especialidade));
        Especialidade result = servico.get(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testEspecialidadeGetByTermoBusca() {
        Mockito.when(repo.busca("termo")).thenReturn(especialidades);
        List<Especialidade> result = servico.get("termo");
        assertEquals(2, result.size());
    }

    @Test
    public void testEspecialidadeSave() {
        Mockito.when(repo.save(especialidade)).thenReturn(especialidade);
        Especialidade result = servico.save(especialidade);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testEspecialidadeDelete() {
        servico.delete(1L);
        Mockito.verify(repo, times(1)).deleteById(1L);
    }

}