package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EPapelTest {
    
    @Test
    public void testEpapelAdmin() {
        EPapel papel = EPapel.ADMIN;
        assertEquals(papel, EPapel.valueOf("ADMIN"));
    }

    @Test
    public void testEpapelAtendente() {
        EPapel papel = EPapel.ATENDENTE;
        assertEquals(papel, EPapel.valueOf("ATENDENTE"));
    }

}