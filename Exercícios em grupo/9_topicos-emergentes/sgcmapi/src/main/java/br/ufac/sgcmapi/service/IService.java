package br.ufac.sgcmapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {

    public Page<T> get(Pageable page);
    public T get(Long id);
    public Page<T> get(String termoBusca, Pageable page);
    public T save(T objeto);
    public void delete(Long id);
    
}
