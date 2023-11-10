package com.biblioteca.proyecto.servicios;

import com.biblioteca.proyecto.entidades.Editorial;
import com.biblioteca.proyecto.exepciones.MiException;
import com.biblioteca.proyecto.repositorios.EditorialRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author benit
 */
@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws MiException{

                validar(nombre);


        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
        
    }
    
    public List<Editorial> listarEditoriales(){
        List<Editorial> editoriales = new ArrayList<Editorial>();
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
    }
    
    public void modificarEditorial(String nombre, String id) throws MiException{
        
        validar(nombre);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Editorial editorial = new Editorial();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        }
        
    }
    
 private void validar(String nombre) throws MiException {

        

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El Nombre no puede ser nulo o estar vacio");

        }

    }

}
