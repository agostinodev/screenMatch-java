package com.example.aluracursos.screenmatch.service;

public interface IConvierteDatos {

        <T> T obtenerDatos(String json, Class<T> clase);


}
