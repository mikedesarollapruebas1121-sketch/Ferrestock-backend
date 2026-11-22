package com.Ferrestock.tienda.controller;

import com.Ferrestock.tienda.model.Categoria;
import com.Ferrestock.tienda.repository.CategoriaRepository;
import com.Ferrestock.tienda.service.CategoriaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping ("/api/categoria")

public class CategoriaController {
    
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService cs) {
        this.categoriaService = cs;
    }
    
    
    @GetMapping ("/")
    public List<Categoria> listar(){
        return categoriaService.ListarCategorias();
    }
    
    @GetMapping ("/{id}")
    public Categoria mostrarCategoria(@PathVariable Integer id){
        return categoriaService.unaCategoria (id);
    }
    
    @PostMapping("/") 
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria registrar(@RequestBody Categoria cat){
    return categoriaService.registrarCategoria(cat);
    }
    
     
    @PutMapping ("/{id}")
    public  Categoria actulizar(@PathVariable Integer id,@RequestBody Categoria cat ){
    return categoriaService.modificarCategoria(id, cat);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void suprimir (@PathVariable Integer id){
        categoriaService.borrarCategoria(id);
    }
    
    
    
    
}
