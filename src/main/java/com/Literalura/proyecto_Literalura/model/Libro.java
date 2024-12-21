package com.Literalura.proyecto_Literalura.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String tituloLibro;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idiomas;

    private int numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.tituloLibro = datosLibro.titulo();
        if (datosLibro.autores() != null && !datosLibro.autores().isEmpty()) {
            this.autor = new Autor(
                    datosLibro.autores().get(0).nombre(),
                    datosLibro.autores().get(0).fechaDeNacimiento(),
                    datosLibro.autores().get(0).fechaDeDefuncion());
        }
        this.idiomas = String.join(", ", datosLibro.idiomas());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    @Override
    public String toString() {
        return
                ", titulo: '" + tituloLibro + '\'' +
                ", autor='" + autor.getNombre() + '\'' +
                ", idioma='" + idiomas + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getAutor() {
        return String.valueOf(autor);
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idiomas;
    }

    public void setIdioma(String idioma) {
        this.idiomas = idioma;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}

