package br.ufac.sgcmapi.service;

import java.time.LocalDate;
import java.util.List;

public interface IService<T> {

    public List<T> get();
    public T get(Long id);
    public List<T> get(String termoBusca);
    public T save(T objeto);
    public void delete(Long id);
    public List<String> get(Long id, LocalDate data);
    
}
