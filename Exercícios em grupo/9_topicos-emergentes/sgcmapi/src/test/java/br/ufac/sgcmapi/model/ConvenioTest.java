package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConvenioTest {

    private Convenio convenio;

    @BeforeEach
    public void setUp() {
        convenio = new Convenio();
    }

    @Test
    public void testConvenioId() {
        Long id = 1L;
        convenio.setId(id);
        assertEquals(id, convenio.getId());
    }

    @Test
    public void testConvenioNome() {
        String nome = "nome";
        convenio.setNome(nome);
        assertEquals(nome, convenio.getNome());
    }

    @Test
    public void testConvenioRazaoSocial() {
        String razaoSocial = "razaoSocial";
        convenio.setRazaoSocial(razaoSocial);
        assertEquals(razaoSocial, convenio.getRazaoSocial());
    }

    @Test
    public void testConvenioCnpj() {
        String cnpj = "cnpj";
        convenio.setCnpj(cnpj);
        assertEquals(cnpj, convenio.getCnpj());
    }

    @Test
    public void testConvenioRepresentante() {
        String representante = "representante";
        convenio.setRepresentante(representante);
        assertEquals(representante, convenio.getRepresentante());
    }

    @Test
    public void testConvenioEmail() {
        String email = "email";
        convenio.setEmail(email);
        assertEquals(email, convenio.getEmail());
    }

    @Test
    public void testConvenioTelefone() {
        String telefone = "telefone";
        convenio.setTelefone(telefone);
        assertEquals(telefone, convenio.getTelefone());
    }

    @Test
    public void testConvenioAtivo() {
        boolean ativo = true;
        convenio.setAtivo(ativo);
        assertEquals(ativo, convenio.isAtivo());
    }

}