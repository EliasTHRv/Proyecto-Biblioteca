package com.biblioteca.proyecto.controlador;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.proyecto.entidades.Editorial;
import com.biblioteca.proyecto.exepciones.MiException;
import com.biblioteca.proyecto.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial") // localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") // localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito", "La editorial fue cargada con exito!!!");
        } catch (MiException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";

        }

        // System.out.println("Nombre: " + nombre);
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("editoriales", editoriales);

        return "editorial_list.html";
    }
}
