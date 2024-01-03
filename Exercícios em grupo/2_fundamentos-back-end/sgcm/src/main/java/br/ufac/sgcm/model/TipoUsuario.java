package br.ufac.sgcm.model;

public enum TipoUsuario {
    ADMIN("admin"),
    ATENDENTE("atendente");

    private String tipoUsuario;

    TipoUsuario (String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario () {
        return tipoUsuario;
    }
    
}
