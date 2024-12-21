package com.Literalura.proyecto_Literalura.repository;

import com.Literalura.proyecto_Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT lb FROM Libro lb WHERE lb.idiomas LIKE %:idioma%")
    List<Libro> obtenerLibrosPorIdioma(@Param("idioma") String idioma);
}
