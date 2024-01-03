package br.ufac.sgcm.model;

import java.io.Serializable;

public class Profissional implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String registroConselho;
    private String telefone;
    private Especialidade especialidade;
    private Unidade unidade;
    
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
    public String getRegistroConselho() {
        return registroConselho;
    }
    public void setRegistroConselho(String registroConselho) {
        this.registroConselho = registroConselho;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Especialidade getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    public Unidade getUnidade() {
        return unidade;
    }
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    
}
