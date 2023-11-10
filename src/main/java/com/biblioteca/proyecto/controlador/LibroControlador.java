package com.biblioteca.proyecto.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.proyecto.entidades.Autor;
import com.biblioteca.proyecto.entidades.Editorial;
import com.biblioteca.proyecto.entidades.Libro;
import com.biblioteca.proyecto.exepciones.MiException;
import com.biblioteca.proyecto.servicios.AutorServicio;
import com.biblioteca.proyecto.servicios.EditorialServicio;
import com.biblioteca.proyecto.servicios.LibroServicio;

@Controller
@RequestMapping("/libro") // localhost:8080/libro
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired 
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") // localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long ISBN, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares,
            @RequestParam String idEditorial, @RequestParam String idAutor, ModelMap modelo) {

        try {
            libroServicio.CrearLibro(ISBN, titulo, ejemplares, idEditorial, idAutor);

            modelo.put("exito", "El libro se cargo con exito.");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }


    @GetMapping("/lista") // localhost:8080/libro/lista
    public String listar(ModelMap modelo){

        List<Libro> libros = libroServicio.listarLibros();

        modelo.addAttribute("libros", libros);

        return "libro_list.html";
        
    }
}
