package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EPapelTest {
    
    @Test
    public void testEpapelAdmin() {
        EPapel papel = EPapel.ROLE_ADMIN;
        assertEquals(papel, EPapel.valueOf("ROLE_ADMIN"));
    }

    @Test
    public void testEpapelAtendente() {
        EPapel papel = EPapel.ROLE_ATENDENTE;
        assertEquals(papel, EPapel.valueOf("ROLE_ATENDENTE"));
    }

}