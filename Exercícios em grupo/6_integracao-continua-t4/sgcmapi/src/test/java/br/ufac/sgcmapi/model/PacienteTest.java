package br.ufac.sgcmapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteTest {

    private Paciente paciente;

    @BeforeEach
    public void setUp() {
        paciente = new Paciente();
    }

    @Test
    public void testPacienteId() {
        Long id = 1L;
        paciente.setId(id);
        assertEquals(id, paciente.getId());
    }

    @Test
    public void testPacienteNome() {
        String nome = "nome";
        paciente.setNome(nome);
        assertEquals(nome, paciente.getNome());
    }

    @Test
    public void testPacienteEmail() {
        String email = "email";
        paciente.setEmail(email);
        assertEquals(email, paciente.getEmail());
    }

    @Test
    public void testPacienteTelefone() {
        String telefone = "telefone";
        paciente.setTelefone(telefone);
        assertEquals(telefone, paciente.getTelefone());
    }

    @Test
    public void testPacienteDataNascimento() {
        LocalDate dataNascimento = LocalDate.now();
        paciente.setDataNascimento(dataNascimento);
        assertEquals(dataNascimento, paciente.getDataNascimento());
    }

    @Test
    public void testPacienteGrupoSanguineo() {
        EGrupoSanguineo grupoSanguineo = EGrupoSanguineo.A_POSITIVO;
        paciente.setGrupoSanguineo(grupoSanguineo);
        assertEquals(grupoSanguineo, paciente.getGrupoSanguineo());
    }

    @Test
    public void testPacienteSexo() {
        ESexo sexo = ESexo.F;
        paciente.setSexo(sexo);
        assertEquals(sexo, paciente.getSexo());
    }

    @Test
    public void testPacienteCep() {
        String cep = "cep";
        paciente.setCep(cep);
        assertEquals(cep, paciente.getCep());
    }

    @Test
    public void testPacienteEndereco() {
        String endereco = "endereco";
        paciente.setEndereco(endereco);
        assertEquals(endereco, paciente.getEndereco());
    }

    @Test
    public void testPacienteCidade() {
        String cidade = "cidade";
        paciente.setCidade(cidade);
        assertEquals(cidade, paciente.getCidade());
    }

    @Test
    public void testPacienteEstado() {
        String estado = "estado";
        paciente.setEstado(estado);
        assertEquals(estado, paciente.getEstado());
    }

}