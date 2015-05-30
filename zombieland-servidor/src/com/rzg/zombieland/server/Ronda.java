package com.rzg.zombieland.server;

/**
 * La ronda es cada una de las �instancias� de juego. Cada partida est� formada por varias rondas,
 * en las que los jugadores correr�n por sus vidas y obtendr�n puntajes parciales.
 * @author nicolas
 *
 */
public class Ronda {
    // Nombre de la ronda, dado por el administrador del sistema.
    private String nombre;
    
    // El tablero que contiene el estado actual del juego.
    private Tablero tablero;
}
