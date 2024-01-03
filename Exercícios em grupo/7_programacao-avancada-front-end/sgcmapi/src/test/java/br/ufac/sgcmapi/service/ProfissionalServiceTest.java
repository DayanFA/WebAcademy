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

import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.repository.ProfissionalRepository;

@ExtendWith(MockitoExtension.class)
public class ProfissionalServiceTest {

    @Mock
    private ProfissionalRepository repo;

    @InjectMocks
    private ProfissionalService servico;

    private Profissional profissional;
    private List<Profissional> profissionais;

    @BeforeEach
    public void setUp() {

        profissional = new Profissional();
        profissional.setId(1L);
        
        Profissional profissional1 = new Profissional();
        profissional1.setId(1L);

        Profissional profissional2 = new Profissional();
        profissional2.setId(2L);

        profissionais = new ArrayList<>();
        profissionais.add(profissional1);
        profissionais.add(profissional2);

    }

    @Test
    public void testProfissionalGetAll() {
        Mockito.when(repo.findAll()).thenReturn(profissionais);
        List<Profissional> result = servico.get();
        assertEquals(2, result.size());
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
        Mockito.when(repo.busca("termo")).thenReturn(profissionais);
        List<Profissional> result = servico.get("termo");
        assertEquals(2, result.size());
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