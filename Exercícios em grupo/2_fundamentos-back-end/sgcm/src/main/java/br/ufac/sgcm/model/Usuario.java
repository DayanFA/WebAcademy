package br.ufac.sgcm.model;

import java.io.Serializable;

public class Usuario implements Serializable{
    private Long id;
    private boolean ativo;
    private String nome_completo;
    private String nome_usuario;
    private TipoUsuario papel;
    private String senha;
    
    public TipoUsuario getPapel() {
        return papel;
    }
    public void setPapel(TipoUsuario papel) {
        this.papel = papel;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public String getNome_completo() {
        return nome_completo;
    }
    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }
    public String getNome_usuario() {
        return nome_usuario;
    }
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
