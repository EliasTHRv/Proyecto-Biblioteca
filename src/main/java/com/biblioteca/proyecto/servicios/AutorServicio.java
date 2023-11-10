package com.biblioteca.proyecto.servicios;

import com.biblioteca.proyecto.entidades.Autor;
import com.biblioteca.proyecto.exepciones.MiException;
import com.biblioteca.proyecto.repositorios.AutorRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author benit
 */
@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(@RequestParam String nombre) throws MiException {
 
        validar(nombre);

        Autor autor = new Autor();
        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutores() {
        
        List<Autor> autores = new ArrayList<Autor>();
        autores = autorRepositorio.findAll();

        return autores;
    }

    public void modificarAutor(String id, String nombre) throws MiException {

        validar(nombre);

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);

        }
    }

    public Autor getOne(String id){
        return autorRepositorio.findById(id).get();
    }

    private void validar(String nombre) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El Nombre no puede ser nulo o estar vacio");

        }

    }

}
