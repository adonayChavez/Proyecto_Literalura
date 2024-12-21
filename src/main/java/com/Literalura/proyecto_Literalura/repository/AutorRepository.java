package com.Literalura.proyecto_Literalura.repository;

import com.Literalura.proyecto_Literalura.model.Autor;
import jakarta.persistence.criteria.From;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository <Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE :año >= a.fechaDeNacimiento AND :año < a.fechaDeDefuncion")
    List<Autor> obtenerAutoresVivosEnDeterminadoAño(@Param("año")int año);

}
