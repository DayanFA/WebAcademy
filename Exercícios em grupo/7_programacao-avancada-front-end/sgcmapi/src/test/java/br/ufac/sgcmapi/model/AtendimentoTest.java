package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AtendimentoTest {

    private Atendimento atendimento;

    @BeforeEach
    public void setUp() {
        atendimento = new Atendimento();
    }

    @Test
    public void testAtendimentoId() {
        Long id = 1L;
        atendimento.setId(id);
        assertEquals(id, atendimento.getId());
    }

    @Test
    public void testAtendimentoData() {
        LocalDate data = LocalDate.now();
        atendimento.setData(data);
        assertEquals(data, atendimento.getData());
    }

    @Test
    public void testAtendimentoHora() {
        LocalTime hora = LocalTime.now();
        atendimento.setHora(hora);
        assertEquals(hora, atendimento.getHora());
    }

    @Test
    public void testAtendimentoProfissional() {
        Profissional profissional = new Profissional();
        profissional.setId(1L);
        atendimento.setProfissional(profissional);
        assertEquals(profissional, atendimento.getProfissional());
    }

    @Test
    public void testAtendimentoConvenio() {
        Convenio convenio = new Convenio();
        convenio.setId(1L);
        atendimento.setConvenio(convenio);
        assertEquals(convenio, atendimento.getConvenio());
    }

    @Test
    public void testAtendimentoPaciente() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        atendimento.setPaciente(paciente);
        assertEquals(paciente, atendimento.getPaciente());
    }

    @Test
    public void testAtendimentoStatus() {
        EStatus status = EStatus.CANCELADO;
        atendimento.setStatus(status);
        assertEquals(status, atendimento.getStatus());
    }

}