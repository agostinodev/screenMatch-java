package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.module.DatosSerie;
import com.aluracursos.screenmatch.module.DatosTemporadas;
import com.aluracursos.screenmatch.module.Serie;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&&apikey=" + System.getenv("API_KEY");
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosSerie> datosSeries = new ArrayList<>();




    public void muestraElMenu() {

        var opcion = -1;

        while (opcion != 0) {

            var menu = """
                    MENU:
                    
                    1- Buscar series.
                    2- Buscaar episodios.
                    3- Mostrar series buscadas.
                    
                    
                    0- Salir.
                    """;

            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");

            }


        }

    }



    private DatosSerie getDatosSerie(){
        System.out.println("Escribe el nombre de la serie que deseas buscar:");
        String nombreSerie = sc.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") +
                    API_KEY);
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);

        return datos;

    }

    private void buscarEpisodioPorSerie(){
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= datosSerie.totalDeTemporadas() ; i++) {

            var json = consumoApi.obtenerDatos(URL_BASE + datosSerie.titulo().replace(" ", "+") + "&Season="+ i + API_KEY);

            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);

        }

        temporadas.forEach(System.out::println);

    }


    private void buscarSerieWeb(){
        DatosSerie datos = getDatosSerie();
        datosSeries.add(datos);
        System.out.println(datos);

    }

    private void mostrarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = datosSeries.stream()
                .map( d -> new Serie(d))
                .collect(Collectors.toList());

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }


























}
