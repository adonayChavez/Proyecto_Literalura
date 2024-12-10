package com.Literalura.proyecto_Literalura.principal;

import com.Literalura.proyecto_Literalura.model.Datos;
import com.Literalura.proyecto_Literalura.model.DatosLibros;
import com.Literalura.proyecto_Literalura.service.ConsumoAPI;
import com.Literalura.proyecto_Literalura.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();



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
                    break;
                default:
                    System.out.println("opcion invalida");

            }

        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+ "?search=" + tituloLibro.replace(" ","+"));
        System.out.println(json);
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l->l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("Libro encontrado ");
            System.out.println(libroBuscado.get());
            System.out.println("Titulo: " + libroBuscado.get().titulo());
            System.out.println("Autores: " + libroBuscado.get().autor());
            System.out.println("Idioma " + libroBuscado.get().idiomas());
            System.out.println("Numero de descargas: " + libroBuscado.get().numeroDeDescargas());
        }
        


    }



}
