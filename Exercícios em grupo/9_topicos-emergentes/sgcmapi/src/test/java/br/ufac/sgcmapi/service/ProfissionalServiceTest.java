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

import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.repository.ProfissionalRepository;

@ExtendWith(MockitoExtension.class)
public class ProfissionalServiceTest {

    @Mock
    private ProfissionalRepository repo;

    @InjectMocks
    private ProfissionalService servico;

    private Profissional profissional;
    private Page<Profissional> profissionais;
    private PageRequest page;

    @BeforeEach
    public void setUp() {

        profissional = new Profissional();
        profissional.setId(1L);
        
        Profissional profissional1 = new Profissional();
        profissional1.setId(1L);

        Profissional profissional2 = new Profissional();
        profissional2.setId(2L);

        List<Profissional> profissionaisList = new ArrayList<>();
        profissionaisList.add(profissional1);
        profissionaisList.add(profissional2);
        profissionais = new PageImpl<>(profissionaisList);

        page = PageRequest.of(0, 5);

    }

    @Test
    public void testProfissionalGetAll() {
        Mockito.when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(profissionais);
        Page<Profissional> result = servico.get(page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testProfissionalGetById() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(profissional));
        Profissional result = servico.get(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testProfissionalGetByTermoBusca() {
        Mockito.when(repo.busca(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(profissionais);
        Page<Profissional> result = servico.get("termo", page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testProfissionalSave() {
        Mockito.when(repo.save(profissional)).thenReturn(profissional);
        Profissional result = servico.save(profissional);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testProfissionalDelete() {
        servico.delete(1L);
        Mockito.verify(repo, times(1)).deleteById(1L);
    }

}