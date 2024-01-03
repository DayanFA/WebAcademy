package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnidadeTest {

    private Unidade unidade;

    @BeforeEach
    public void setUp() {
        unidade = new Unidade();
    }

    @Test
    public void testUnidadeId() {
        Long id = 1L;
        unidade.setId(id);
        assertEquals(id, unidade.getId());
    }

    @Test
    public void testUnidadeNome() {
        String nome = "nome";
        unidade.setNome(nome);
        assertEquals(nome, unidade.getNome());
    }

    @Test
    public void testUnidadeEndereco() {
        String endereco = "endereco";
        unidade.setEndereco(endereco);
        assertEquals(endereco, unidade.getEndereco());
    }
    
}
