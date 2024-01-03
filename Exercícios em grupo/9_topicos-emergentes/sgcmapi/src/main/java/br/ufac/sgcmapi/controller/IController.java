package br.ufac.sgcmapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IController<T> {

    public ResponseEntity<?> get(Pageable page);
    public ResponseEntity<?> get(Long id);
    public ResponseEntity<?> get(String termoBusca, Pageable page);
    public ResponseEntity<T> insert(T objeto);
    public ResponseEntity<T> update(T objeto);
    public ResponseEntity<?> delete(Long id);
    
}
