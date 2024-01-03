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

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.repository.PacienteRepository;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    private PacienteRepository repo;

    @InjectMocks
    private PacienteService servico;

    private Paciente Paciente;
    private List<Paciente> Pacientes;

    @BeforeEach
    public void setUp() {
        
        Paciente = new Paciente();
        Paciente.setId(1L);
        
        Paciente Paciente1 = new Paciente();
        Paciente1.setId(1L);
        
        Paciente Paciente2 = new Paciente();
        Paciente2.setId(2L);

        Pacientes = new ArrayList<>();
        Pacientes.add(Paciente1);
        Pacientes.add(Paciente2);

    }

    @Test
    public void testPacienteGetAll() {
        Mockito.when(repo.findAll()).thenReturn(Pacientes);
        List<Paciente> result = servico.get();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testPacienteDelete() {
        Long id = 1L;
        Mockito.doNothing().when(repo).deleteById(id);
        servico.delete(id);
        Mockito.verify(repo, Mockito.times(1)).deleteById(id);
    }
    
}