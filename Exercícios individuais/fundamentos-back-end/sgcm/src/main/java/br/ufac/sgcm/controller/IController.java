package br.ufac.sgcm.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IController<T> {

    List<T> get();
    T get(Long id);
    int save(T objeto);
    int delete(Long id);
    List<T> processListRequest(
        HttpServletRequest request);
    T processFormRequest(
        HttpServletRequest request,
        HttpServletResponse response);
    
}
