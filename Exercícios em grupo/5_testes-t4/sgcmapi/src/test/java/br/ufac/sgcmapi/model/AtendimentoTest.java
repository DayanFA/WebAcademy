package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(1L, atendimento.getId());
    }
    
}
