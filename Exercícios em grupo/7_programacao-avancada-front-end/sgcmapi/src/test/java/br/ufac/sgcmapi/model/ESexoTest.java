package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ESexoTest {

    @Test
    public void testEsexoM() {
        ESexo sexo = ESexo.M;
        assertEquals(sexo, ESexo.valueOf("M"));
    }

    @Test
    public void testEsexoF() {
        ESexo sexo = ESexo.F;
        assertEquals(sexo, ESexo.valueOf("F"));
    }

}