package com.biblioteca.proyecto.servicios;

import com.biblioteca.proyecto.entidades.Autor;
import com.biblioteca.proyecto.entidades.Editorial;
import com.biblioteca.proyecto.entidades.Libro;
import com.biblioteca.proyecto.exepciones.MiException;
import com.biblioteca.proyecto.repositorios.AutorRepositorio;
import com.biblioteca.proyecto.repositorios.LibroRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.proyecto.repositorios.EditorialRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author benit
 */
@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void CrearLibro(Long ISBN, String Titulo, Integer Ejemplares, String idEditorial, String idAutor) throws MiException {

        validar(ISBN, Titulo, Ejemplares, idEditorial, idAutor);

        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();

        libro.setISBN(ISBN);
        libro.setTitulo(Titulo);
        libro.setEjemplares(Ejemplares);

        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList<Libro>();
        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long ISBN, String Titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {

        validar(ISBN, Titulo, ejemplares, idEditorial, idAutor);

        Optional<Libro> respuesta = libroRepositorio.findById(ISBN);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();

        }

        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setTitulo(Titulo);

            libro.setAutor(autor);

            libro.setEditorial(editorial);

            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);

        }
    }

    private void validar(Long ISBN, String Titulo, Integer Ejemplares, String idEditorial, String idAutor) throws MiException {

        if (ISBN == null) {
            throw new MiException("El ISBN no puede ser nulo");
        }

        if (Titulo.isEmpty() || Titulo == null) {
            throw new MiException("El Titulo no puede ser nulo o estar vacio");

        }

        if (Ejemplares == null) {
            throw new MiException("El Ejemplares no puede ser nulo");
        }

        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("El idEditorial no puede ser nulo o estar vacio");

        }

        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El idAutor no puede ser nulo o estar vacio");

        }

    }

}
