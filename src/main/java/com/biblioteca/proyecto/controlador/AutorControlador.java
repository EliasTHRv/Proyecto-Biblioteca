package com.biblioteca.proyecto.controlador;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.proyecto.entidades.Autor;
import com.biblioteca.proyecto.exepciones.MiException;
import com.biblioteca.proyecto.servicios.AutorServicio;

@Controller
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {

        try {
            autorServicio.crearAutor(nombre);
            modelo.put("exito", "El autor se cargo con exito.");

        } catch (MiException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());

            return "autor_form.html";

        }

        // System.out.println("Nombre: " + nombre);
        return "index.html";
    }

    @GetMapping("/lista") // localhost:8080/autor/lista
    public String listar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores();

        modelo.addAttribute("autores", autores);

        return "autor_list.html";

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
       
        modelo.put("autor", autorServicio.getOne(id));

        return "autor_modificar.html";
    }

}
