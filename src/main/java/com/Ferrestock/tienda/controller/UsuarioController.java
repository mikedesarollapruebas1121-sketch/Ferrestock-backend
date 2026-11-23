
package com.Ferrestock.tienda.controller;

import com.Ferrestock.tienda.model.Usuario;
import com.Ferrestock.tienda.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService us) {
        this.usuarioService = us;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }
    
    
@PostMapping("/login")
public Usuario iniciarSesion(@RequestBody Usuario usuario) {

    return usuarioService.iniciarSesion(
            usuario.getCorreo(),
            usuario.getContrasenal()
    );
}

}
