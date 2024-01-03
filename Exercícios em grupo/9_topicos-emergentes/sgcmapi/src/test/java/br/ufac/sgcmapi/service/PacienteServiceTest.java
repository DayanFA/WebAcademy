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

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.repository.PacienteRepository;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    private PacienteRepository repo;

    @InjectMocks
    private PacienteService servico;

    private Paciente paciente;
    private Page<Paciente> pacientes;
    private PageRequest page;

    @BeforeEach
    public void setUp() {

        paciente = new Paciente();
        paciente.setId(1L);
        
        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);

        Paciente paciente2 = new Paciente();
        paciente2.setId(2L);

        List<Paciente> pacientesList = new ArrayList<>();
        pacientesList.add(paciente1);
        pacientesList.add(paciente2);
        pacientes = new PageImpl<>(pacientesList);

        page = PageRequest.of(0, 5);

    }

    @Test
    public void testPacienteGetAll() {
        Mockito.when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(pacientes);
        Page<Paciente> result = servico.get(page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testPacienteGetById() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(paciente));
        Paciente result = servico.get(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testPacienteGetByTermoBusca() {
        Mockito.when(repo.busca(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(pacientes);
        Page<Paciente> result = servico.get("termo", page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testPacienteSave() {
        Mockito.when(repo.save(paciente)).thenReturn(paciente);
        Paciente result = servico.save(paciente);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testPacienteDelete() {
        servico.delete(1L);
        Mockito.verify(repo, times(1)).deleteById(1L);
    }

}