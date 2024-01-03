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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.repository.EspecialidadeRepository;

@ExtendWith(MockitoExtension.class)
public class EspecialidadeServiceTest {

    @Mock
    private EspecialidadeRepository repo;

    @InjectMocks
    private EspecialidadeService servico;

    private Especialidade especialidade;
    private Page<Especialidade> especialidades;
    private PageRequest page;

    @BeforeEach
    public void setUp() {

        especialidade = new Especialidade();
        especialidade.setId(1L);
        
        Especialidade especialidade1 = new Especialidade();
        especialidade1.setId(1L);

        Especialidade especialidade2 = new Especialidade();
        especialidade2.setId(2L);

        List<Especialidade> especialidadesList = new ArrayList<>();
        especialidadesList.add(especialidade1);
        especialidadesList.add(especialidade2);
        especialidades = new PageImpl<>(especialidadesList);

        page = PageRequest.of(0, 5);

    }

    @Test
    public void testEspecialidadeGetAll() {
        Mockito.when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(especialidades);
        Page<Especialidade> result = servico.get(page);
        assertEquals(2, result.getTotalElements());
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
        Mockito.when(repo.busca(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(especialidades);
        Page<Especialidade> result = servico.get("termo", page);
        assertEquals(2, result.getTotalElements());
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