package br.ufac.sgcmapi.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Paciente implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private EGrupoSanguineo grupoSanguineo;
    private ESexo sexo;
    private String cep;
    private String endereco;
    private String cidade;
    private String estado;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public EGrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }
    public void setGrupoSanguineo(EGrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    public ESexo getSexo() {
        return sexo;
    }
    public void setSexo(ESexo sexo) {
        this.sexo = sexo;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
