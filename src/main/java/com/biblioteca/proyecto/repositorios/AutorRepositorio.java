package com.biblioteca.proyecto.repositorios;

import com.biblioteca.proyecto.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author benit
 */
@Repository
public interface AutorRepositorio extends JpaRepository<Autor,String> {
    
}
