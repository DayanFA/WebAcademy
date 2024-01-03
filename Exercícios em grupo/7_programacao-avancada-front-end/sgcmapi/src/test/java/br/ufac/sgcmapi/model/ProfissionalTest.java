package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfissionalTest {

    private Profissional profissional;

    @BeforeEach
    public void setUp() {
        profissional = new Profissional();
    }

    @Test
    public void testProfissionalId() {
        Long id = 1L;
        profissional.setId(id);
        assertEquals(id, profissional.getId());
    }

    @Test
    public void testProfissionalNome() {
        String nome = "nome";
        profissional.setNome(nome);
        assertEquals(nome, profissional.getNome());
    }

    @Test
    public void testProfissionalEmail() {
        String email = "email";
        profissional.setEmail(email);
        assertEquals(email, profissional.getEmail());
    }

    @Test
    public void testProfissionalRegistroConselho() {
        String registroConselho = "registroConselho";
        profissional.setRegistroConselho(registroConselho);
        assertEquals(registroConselho, profissional.getRegistroConselho());
    }

    @Test
    public void testProfissionalTelefone() {
        String telefone = "telefone";
        profissional.setTelefone(telefone);
        assertEquals(telefone, profissional.getTelefone());
    }

    @Test
    public void testProfissionalEspecialidade() {
        Especialidade especialidade = new Especialidade();
        especialidade.setId(1L);
        profissional.setEspecialidade(especialidade);
        assertEquals(especialidade, profissional.getEspecialidade());
    }

    @Test
    public void testProfissionalUnidade() {
        Unidade unidade = new Unidade();
        unidade.setId(1L);
        profissional.setUnidade(unidade);
        assertEquals(unidade, profissional.getUnidade());
    }
    
}
