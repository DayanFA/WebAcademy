package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EStatusTest {

    @Test
    public void testEstatusProximo() {

        EStatus cancelado = EStatus.CANCELADO;
        EStatus agendado = EStatus.AGENDADO;
        EStatus confirmado = EStatus.CONFIRMADO;
        EStatus chegada = EStatus.CHEGADA;
        EStatus atendimento = EStatus.ATENDIMENTO;
        EStatus encerrado = EStatus.ENCERRADO;

        assertEquals(cancelado, cancelado.proximo());
        assertEquals(confirmado, agendado.proximo());
        assertEquals(chegada, confirmado.proximo());
        assertEquals(atendimento, chegada.proximo());
        assertEquals(encerrado, atendimento.proximo());
        assertEquals(encerrado, encerrado.proximo());

    }
    
}
