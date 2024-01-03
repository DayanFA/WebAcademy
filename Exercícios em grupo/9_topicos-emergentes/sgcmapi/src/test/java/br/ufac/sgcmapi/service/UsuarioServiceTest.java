package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService servico;

    private Usuario usuario;
    private Page<Usuario> usuarios;
    private PageRequest page;

    @BeforeEach
    public void setUp() {

        usuario = new Usuario();
        usuario.setId(1L);
        
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);

        List<Usuario> usuariosList = new ArrayList<>();
        usuariosList.add(usuario1);
        usuariosList.add(usuario2);
        usuarios = new PageImpl<>(usuariosList);

        page = PageRequest.of(0, 5);

    }

    @Test
    public void testUsuarioGetAll() {
        Mockito.when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(usuarios);
        Page<Usuario> result = servico.get(page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testUsuarioGetById() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario result = servico.get(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUsuarioGetByTermoBusca() {
        Mockito.when(repo.busca(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(usuarios);
        Page<Usuario> result = servico.get("termo", page);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testUsuarioSave() {
        Mockito.when(repo.save(usuario)).thenReturn(usuario);
        Usuario result = servico.save(usuario);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUsuarioDelete() {
        servico.delete(1L);
        Mockito.verify(repo, times(1)).deleteById(1L);
    }

}