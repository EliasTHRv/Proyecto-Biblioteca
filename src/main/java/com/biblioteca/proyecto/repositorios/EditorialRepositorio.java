package com.biblioteca.proyecto.repositorios;


import com.biblioteca.proyecto.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author benit
 */
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,String> {
    
}
