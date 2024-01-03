package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EGrupoSanguineoTest {

    @Test
    public void testEgrupoSanguineoAPositivo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.A_POSITIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("A_POSITIVO"));
    }

    @Test
    public void testEgrupoSanguineoANegativo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.A_NEGATIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("A_NEGATIVO"));
    }

    @Test
    public void testEgrupoSanguineoBPositivo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.B_POSITIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("B_POSITIVO"));
    }

    @Test
    public void testEgrupoSanguineoBNegativo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.B_NEGATIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("B_NEGATIVO"));
    }

    @Test
    public void testEgrupoSanguineoABPositivo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.AB_POSITIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("AB_POSITIVO"));
    }

    @Test
    public void testEgrupoSanguineoABNegativo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.AB_NEGATIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("AB_NEGATIVO"));
    }

    @Test
    public void testEgrupoSanguineoOPositivo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.O_POSITIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("O_POSITIVO"));
    }

    @Test
    public void testEgrupoSanguineoONegativo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.O_NEGATIVO;
        assertEquals(grupoSanguineo, EGrupoSanguineo.valueOf("O_NEGATIVO"));
    }

}