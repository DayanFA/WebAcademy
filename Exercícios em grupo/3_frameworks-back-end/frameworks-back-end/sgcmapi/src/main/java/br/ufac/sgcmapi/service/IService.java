package br.ufac.sgcmapi.service;

import java.util.List;

public interface IService<T> {

    public List<T> get();
    public T get(Long id);
    public List<T> get(String termoBusca);
    public T save(T objeto);
    public void delete(Long id);
    public List<T> getByPapel(String papel);
    public List<T> getByNomeCompleto(String nc);
    public List<T> getAtivos(boolean ativo);
    
}
