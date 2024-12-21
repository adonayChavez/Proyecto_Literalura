package com.Literalura.proyecto_Literalura.principal;

import com.Literalura.proyecto_Literalura.model.*;
import com.Literalura.proyecto_Literalura.repository.AutorRepository;
import com.Literalura.proyecto_Literalura.repository.LibroRepository;
import com.Literalura.proyecto_Literalura.service.ConsumoAPI;
import com.Literalura.proyecto_Literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    Scanner teclado = new Scanner(System.in);

    private static final String URL_BASE = "https://gutendex.com/books/";

    @Autowired
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    @Autowired
    private ConvierteDatos conversor = new ConvierteDatos();

    private  List<DatosLibro> datosLibro = new ArrayList<>();

    @Autowired
    private LibroRepository repositorio;

    @Autowired
    private AutorRepository autorRepository;

    private List<Libro> libros;

    private List<Autor> autores;

    private Optional<Libro> libroBuscado;

    @Autowired
    public Principal(LibroRepository repository, AutorRepository  autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros  registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdiomas();
                    break;
                default:
                    System.out.println("opcion invalida");

            }

        }
    }

    private DatosLibro getDatosLibros(){
        System.out.println("Ingrese el libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        System.out.println(json);

        Datos datos = null;
        try {
            datos = conversor.obtenerDatos(json, Datos.class);
        } catch
        (Exception e) { e.printStackTrace();
        } if
        (datos != null && datos.resultados() != null && !datos.resultados().isEmpty()) {
            DatosLibro datosLibro = datos.resultados().get(0);
            return datosLibro; }
        else {
            System.out.println("No se encontraron resultados");
            return null;
        }
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datos = getDatosLibros();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);

    }

    private void mostrarLibrosRegistrados() {
        libros = repositorio.findAll();
        libros.stream()
               .forEach(System.out::println);
    }

    private void mostrarAutoresRegistrados() {
        List<Autor> autores = (List<Autor>) autorRepository.findAll();
        autores.forEach(autor -> {
            System.out.println(
                    "Nombre: " + autor.getNombre() + ", " +
                            "Fecha de Nacimiento: " + autor.getFechaDeNacimiento() + ", " +
                            "Fecha de Defuncion: " + autor.getFechaDeDefuncion());

        });
    }

    private void listarAutoresVivos() {
        System.out.println("ingrese el año que desea verificar y ver sus actores");
        var año = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autoresVivos = autorRepository.obtenerAutoresVivosEnDeterminadoAño(año);
        autoresVivos.stream()
                .forEach(System.out::println);
    }

    private void listarLibrosPorIdiomas() {
        System.out.println("Igrese el idioma");
        var idioma = teclado.nextLine();

        List<Libro> libros = repositorio.obtenerLibrosPorIdioma(idioma);
        libros.stream()
                .forEach(System.out::println);
    }
}
