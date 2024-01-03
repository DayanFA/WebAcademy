package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspecialidadeTest {

    private Especialidade especialidade;

    @BeforeEach
    public void setUp() {
        especialidade = new Especialidade();
    }

    @Test
    public void testEspecialidadeId() {
        Long id = 1L;
        especialidade.setId(id);
        assertEquals(id, especialidade.getId());
    }

    @Test
    public void testEspecialidadeNome() {
        String nome = "nome";
        especialidade.setNome(nome);
        assertEquals(nome, especialidade.getNome());
    }

}