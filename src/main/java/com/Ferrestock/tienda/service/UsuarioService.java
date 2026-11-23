
package com.Ferrestock.tienda.service;

import com.Ferrestock.tienda.model.Usuario;
import com.Ferrestock.tienda.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository u) {
        this.usuarioRepository = u;
    }

    public List<Usuario> ListarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario unUsuario(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuario no existente"
            )
        );
    }

    public Usuario registrarUsuario(Usuario usuario) {

        validarUsuario(usuario);

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Correo ya registrado"
            );
        }

        usuario.setIdUsuario(null);
        usuario.setContrasenal(
                passwordEncoder.encode(usuario.getContrasenal())
        );
        usuario.setRol("CLIENTE");

        return usuarioRepository.save(usuario);
    }

    private void validarUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Datos obligatorios"
            );
        }

        if (usuario.getNombre() == null ||
                usuario.getNombre().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Nombre obligatorio"
            );
        }

        if (usuario.getCorreo() == null ||
                usuario.getCorreo().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Correo obligatorio"
            );
        }

        if (usuario.getContrasenal() == null ||
                usuario.getContrasenal().length() < 8) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La contraseña debe tener mínimo 8 caracteres"
            );
        }

        usuario.setNombre(usuario.getNombre().trim());
        usuario.setCorreo(usuario.getCorreo().trim());
    }
    
public Usuario iniciarSesion(String correo, String contrasena) {

    if (correo == null || correo.isBlank()
            || contrasena == null || contrasena.isBlank()) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Correo y contraseña obligatorios"
        );
    }

    Usuario usuario = usuarioRepository
            .findByCorreo(correo.trim())
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Credenciales incorrectas"
            ));

    if (!passwordEncoder.matches(
            contrasena, usuario.getContrasenal())) {
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Credenciales incorrectas"
        );
    }

    return usuario;
}

}
