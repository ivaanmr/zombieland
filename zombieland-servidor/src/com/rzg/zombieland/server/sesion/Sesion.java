package com.rzg.zombieland.server.sesion;

import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.EnviaPeticiones;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;

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

    // La partida a la que est� asociada el jugador. Puede ser null.
    private Partida partida;
    
    // El objeto que env�a peticiones al otro lado.
    private EnviaPeticiones hilo;
    
    /**
     * Construye una sesi�n a partir del jugador.
     * @param jugador
     * @param hilo - el hilo de escucha que atiende esta sesi�n.
     * @throws NullPointerException si el jugador es nulo.
     */
    public Sesion(Jugador jugador, EnviaPeticiones hilo) {
        if (jugador == null)
            throw new NullPointerException("El jugador no puede ser null");
        if (hilo == null)
            throw new NullPointerException("El hilo no puede ser null");
        this.jugador = jugador;
        id = UUID.randomUUID();
        this.hilo = hilo;
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


    /**
     * @return la partida jugada en esta sesi�n. 
     */
    public Partida getPartida() {
        return partida;
    }
    
    /**
     * Establece la partida que el jugador comienza.
     * @param partida
     * @throws ZombielandException 
     */
    public void setPartida(Partida partida) throws ZombielandException {
        abandonarPartidaActual();
        this.partida = partida;
    }

    /**
     * Env�a una petici�n al cliente asociado a la sesi�n.
     * @throws ZombielandException 
     */
    public void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException {
        hilo.enviarPeticion(peticion);
    }

    /**
     * Hace que el jugador abandone la partida.
     * @throws ZombielandException 
     */
    private void abandonarPartidaActual() throws ZombielandException {
        if (partida == null)
            return;
        partida.removerJugador(jugador);
        partida = null;
    }
}
