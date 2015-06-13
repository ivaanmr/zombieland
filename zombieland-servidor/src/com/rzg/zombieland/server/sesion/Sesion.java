package com.rzg.zombieland.server.sesion;

import java.util.UUID;

/**
 * Rastrea el estado de un jugador: si ha iniciado sesi�n, en qu� partida est� jugando y a qu� 
 * personaje est� controlando.
 * @author nicolas
 *
 */
public class Sesion {
    // El jugador relacionado a la sesi�n.
    private Jugador jugador;
    
    // El ID �nico de la sesi�n.
    private UUID id;
    
    /**
     * Construye una sesi�n a partir del jugador.
     * @param jugador
     * @throws NullPointerException si el jugador es nulo.
     */
    public Sesion(Jugador jugador) {
        if (jugador == null)
            throw new NullPointerException("El jugador no puede ser null");
        this.jugador = jugador;
        id = UUID.randomUUID();
    }

    /**
     * @return el ID de la sesi�n.
     */
    public UUID getId() {
        return id;
    }
    
    /**
     * @return el jugador asociado a la sesi�n.
     */
    public Jugador getJugador() {
        return jugador;
    }
}
