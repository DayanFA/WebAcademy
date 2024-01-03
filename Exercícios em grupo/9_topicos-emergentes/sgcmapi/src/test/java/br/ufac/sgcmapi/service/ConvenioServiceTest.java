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

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.repository.ConvenioRepository;

@ExtendWith(MockitoExtension.class)
public class ConvenioServiceTest {

    @Mock
    private ConvenioRepository repo;

    @InjectMocks
    private ConvenioService servico;

    private Convenio convenio;
    private Page<Convenio> convenios;
    private PageRequest page;

    @BeforeEach
    public void setUp() {

        convenio = new Convenio();
        convenio.setId(1L);
        
        Convenio convenio1 = new Convenio();
        convenio1.setId(1L);

        Convenio convenio2 = new Convenio();
        convenio2.setId(2L);

        List<Convenio> conveniosList = new ArrayList<>();
        conveniosList.add(convenio1);
        conveniosList.add(convenio2);
        convenios = new PageImpl<>(conveniosList);

        page = PageRequest.of(0, 5);

    }

    @Test
    public void testConvenioGetAll() {
        Mockito.when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(convenios);
        Page<Convenio> result = servico.get(page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testConvenioGetById() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(convenio));
        Convenio result = servico.get(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testConvenioGetByTermoBusca() {
        Mockito.when(repo.busca(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(convenios);
        Page<Convenio> result = servico.get("termo", page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testConvenioSave() {
        Mockito.when(repo.save(convenio)).thenReturn(convenio);
        Convenio result = servico.save(convenio);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testConvenioDelete() {
        servico.delete(1L);
        Mockito.verify(repo, times(1)).deleteById(1L);
    }

}