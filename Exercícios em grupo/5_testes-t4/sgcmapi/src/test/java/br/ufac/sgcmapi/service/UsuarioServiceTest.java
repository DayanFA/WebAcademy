package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService servico;

    private Usuario usuario;
    private List<Usuario> usuarios;

    @BeforeEach
    public void setUp() {
        
        usuario = new Usuario();
        usuario.setId(1L);
        
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);

        usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

    }

    @Test
    public void testUsuarioGetAll() {
        Mockito.when(repo.findAll()).thenReturn(usuarios);
        List<Usuario> result = servico.get();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testUsuarioDelete() {
        Long id = 1L;
        Mockito.doNothing().when(repo).deleteById(id);
        servico.delete(id);
        Mockito.verify(repo, Mockito.times(1)).deleteById(id);
    }
    
}