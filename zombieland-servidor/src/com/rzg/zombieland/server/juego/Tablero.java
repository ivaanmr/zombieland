package com.rzg.zombieland.server.juego;

import java.util.List;

import com.rzg.zombieland.server.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.server.misc.Coordenada;

/**
 * El tablero de juego, que contiene obst�culos, humanos y zombies.
 * @author nicolas
 *
 */
public class Tablero {
    // El tablero en s�, con todos sus actores.
    private EntidadTablero[][] matriz; 
    
    // Personajes que participan del juego.
    private List<Personaje> personajes;
    
    // Posici�n en el listado de personajes del que se mover� primero.
    private int primerPersonaje;
    
    /**
     * Constructor por defecto.
     */
    public Tablero() {
        // TODO implementar.
    }
    
    /**
     * Devuelve la proyecci�n del tablero entre las dos esquinas dadas. 
     * @param desde
     * @param hasta
     * @return
     */
    public ProyeccionTablero getProyeccion(Coordenada superiorIzquierda,
                                           Coordenada inferiorDerecha) {
        // TODO implementar.
        return null;
    }
    
    /**
     * Obtiene la entidad por coordenada.
     * @param coordenada
     * @return la entidad en la coordenada dada, o null si no hay ninguna.
     */
    public EntidadTablero getEntidadEn(Coordenada coordenada) {
        // TODO implementar.
        return null;
    }
    
    /**
     * Mueve una entidad.
     * @param desde - coordenada donde la entidad original se encuentra. 
     * @param hasta - coordenada de destino. Debe estar vac�a.
     */
    public void moverEntidad(Coordenada desde, Coordenada hasta) {
        // TODO implementar.
    }
}
